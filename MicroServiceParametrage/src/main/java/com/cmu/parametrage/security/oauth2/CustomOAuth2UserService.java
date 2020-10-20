package com.cmu.parametrage.security.oauth2;

import com.cmu.parametrage.Exception.OAuth2AuthenticationProcessingException;
import com.cmu.parametrage.dao.UtilisateurRepository;
import com.cmu.parametrage.entities.AuthProvider;
import com.cmu.parametrage.entities.Utilisateur;
import com.cmu.parametrage.security.UserPrincipal;
import com.cmu.parametrage.security.oauth2.user.OAuth2UserInfo;
import com.cmu.parametrage.security.oauth2.user.OAuth2UserInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email non trouvé du fournisseur OAuth2");
        }

        Optional<Utilisateur> userOptional = utilisateurRepository.findByEmail(oAuth2UserInfo.getEmail());
        Utilisateur utilisateur;
        if (userOptional.isPresent()) {
            utilisateur = userOptional.get();
            if (!utilisateur.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Vous êtes inscrit avec " +
                        utilisateur.getProvider() + " compte. S'il vous plaît utiliser votre " + utilisateur.getProvider() + " compte pour se connecter.");
            }
            utilisateur = updateExistingUser(utilisateur, oAuth2UserInfo);
        } else {
            utilisateur = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(utilisateur, oAuth2User.getAttributes());
    }

    private Utilisateur registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        utilisateur.setProviderId(oAuth2UserInfo.getId());
        utilisateur.setEmail(oAuth2UserInfo.getEmail());
        utilisateur.setImageUrl(oAuth2UserInfo.getImageUrl());
        return utilisateurRepository.save(utilisateur);
    }

    private Utilisateur updateExistingUser(Utilisateur existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return utilisateurRepository.save(existingUser);
    }
}
