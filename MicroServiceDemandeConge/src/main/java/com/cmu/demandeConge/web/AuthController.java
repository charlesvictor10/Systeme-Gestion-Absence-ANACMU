package com.cmu.demandeConge.web;

import com.cmu.demandeConge.Exception.BadRequestException;
import com.cmu.demandeConge.config.MailService;
import com.cmu.demandeConge.dao.PasswordResetTokenRepository;
import com.cmu.demandeConge.dao.RoleRepository;
import com.cmu.demandeConge.dao.UtilisateurRepository;
import com.cmu.demandeConge.entities.*;
import com.cmu.demandeConge.payload.*;
import com.cmu.demandeConge.security.TokenProvider;
import com.cmu.demandeConge.util.JIDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private TokenProvider tokenProvider;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (utilisateurRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Cet adresse email est déjà utilisé.");
        }

        // Creating user's account
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(signUpRequest.getEmail());
        utilisateur.setPassword(signUpRequest.getPassword());
        utilisateur.setInsertDate(new Date());
        utilisateur.setInsertUserId(Math.toIntExact(signUpRequest.getId()));
        utilisateur.setActived(true);
        utilisateur.setProvider(AuthProvider.local);
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateur.setDoitChangerSonMdp(true);
        Utilisateur result = utilisateurRepository.save(utilisateur);
        addRoleToUser(signUpRequest.getId(), RoleEnum.AG.getId());

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/currentUser")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Utilisateur crée avec succé"));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    public Role saveRole(@Valid @RequestBody RoleRequest roleRequest) {
        if (roleRepository.existsByCode(roleRequest.getCode())) {
            throw new BadRequestException("Ce rôle est déjà enregistré.");
        }

        Role role = new Role();
        role.setCode(roleRequest.getCode());
        role.setLib(roleRequest.getLib());
        role.setDescription(roleRequest.getDescription());

        return roleRepository.save(role);
    }

    @RequestMapping(value = "/addRoleToUser", method = RequestMethod.POST)
    public void addRoleToUser(Long idUtilisateur, Long idRole) {
        Utilisateur utilisateur = utilisateurRepository.getOne(idUtilisateur);
        Role role = roleRepository.getOne(idRole);
        utilisateur.getRoles().add(role);
    }

    ////////////////////////////////////////////////////Change password/////////////////////////////////////
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ResponseEntity<?> updatePassword(@RequestBody Utilisateur utilisateur) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUser = utilisateurRepository.findByEmailIgnoreCase(authentication.getName());
        if(utilisateur.getNewPassword() == null || utilisateur.getConfirmPassword() == null) {
            throw new RuntimeException("Le nouveau mot de passe ou la confirmation ne peut être nul");
        } else if(!utilisateur.getNewPassword().equalsIgnoreCase(utilisateur.getConfirmPassword())){
            throw new RuntimeException("Les deux mots de passe sont différents.");
        } else if(!passwordEncoder.matches(utilisateur.getPassword(),currentUser.getPassword())) {
            throw new RuntimeException("Votre ancien mot de passe est incorrecte.");
        }
        currentUser.setPassword(passwordEncoder.encode(utilisateur.getNewPassword()));
        currentUser.setDateChangementMdp(new Date());
        currentUser.setUpdateDate(new Date());
        utilisateurRepository.save(currentUser);
        return ResponseEntity.ok().body(200);
    }

    ///////////////////////////////////////////Reset Password/////////////////////////////////////////////////
    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public ResponseEntity<?> forgotUserPassword(@Valid @RequestBody PasswordForgotRequest passwordForgotRequest) {
        Utilisateur existingUser = utilisateurRepository.findByEmailIgnoreCase(passwordForgotRequest.getEmail());
        if(existingUser != null) {

            //////////////////Création du token//////////////////////
            PasswordResetToken token = new PasswordResetToken(existingUser);
            token.setToken(UUID.randomUUID().toString());
            token.setUtilisateur(existingUser);
            token.setExpiryDate(24*60);
            token.setInsertDate(JIDate.now());

            //////////Enregistrement du token///////////////////////
            passwordResetTokenRepository.save(token);

            ////////////Création de l'email///////////////////////
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existingUser.getEmail());
            mailMessage.setSubject("Réinitialisation de mot de passe!");
            mailMessage.setFrom("victorcharleswade@gmail.com");
            mailMessage.setText("Pour compléter le processus de réinitialisation de votre mot de passe, veuillez cliquer sur ce lien:" + "http://localhost:8080/confirmResetPassword?token="+token.getToken());

            ////////////////Envoie de l'email////////////////////
            mailService.send(mailMessage);

            return ResponseEntity.ok().body(200);
        } else {
            throw new RuntimeException("Echec de l'envoie du mail de réinitialisation.");
        }
    }

    @RequestMapping(value = "/confirmResetPassword", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> validateResetToken(@RequestParam("token") String token) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if(passwordResetToken != null){
            Utilisateur utilisateur = utilisateurRepository.findByEmailIgnoreCase(passwordResetToken.getUtilisateur().getEmail());
            utilisateur.setActived(true);
            utilisateurRepository.save(utilisateur);

            passwordResetToken.setUpdateDate(JIDate.now());
            passwordResetTokenRepository.save(passwordResetToken);
        } else {
            throw new RuntimeException("Ce lien est incorrect ou endommagé.");
        }
        return ResponseEntity.ok().body(200);
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public ResponseEntity<?> resetUserPassword(Utilisateur utilisateur) {
        if(utilisateur.getEmail() != null) {
            Utilisateur tokenUser = utilisateurRepository.findByEmailIgnoreCase(utilisateur.getEmail());
            tokenUser.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
            utilisateurRepository.save(tokenUser);
            return ResponseEntity.ok().body(200);
        } else {
            throw new RuntimeException("Ce lien est incorrect ou endommagé.");
        }
    }
}
