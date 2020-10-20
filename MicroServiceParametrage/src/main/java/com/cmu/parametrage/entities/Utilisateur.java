package com.cmu.parametrage.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date insertDate;
    @Column(name = "insert_user_id", nullable = false, updatable = false)
    private int insertUserId;
    private Date updateDate;
    @Column(name = "update_user_id", insertable = false)
    private Integer updateUserId;
    @Email
    @Column(nullable = false,unique = true)
    private String email;
    private String imageUrl;
    @Column(nullable = false)
    private Boolean emailVerified = false;
    private String password;
    private boolean actived;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;
    private String providerId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePremiereConnexion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDerniereConnexion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateChangementMdp;
    private Integer nbEssaiConnexion;
    private boolean doitChangerSonMdp;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDerniereDeconnexion;
    private String raisonBlocage;
    @JoinColumn(name = "agent_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Agent agent;
    @ManyToMany
    private Collection<Role> roles = new ArrayList<>();
    @Transient
    private String newPassword;
    @Transient
    private String confirmPassword;

    public Utilisateur() {
    }

    public Utilisateur(@Email String email, String password, Agent agent) {
        this.email = email;
        this.password = password;
        this.agent = agent;
    }

    public Utilisateur(String email, String password, boolean actived, AuthProvider provider, String providerId) {
        this.email = email;
        this.password = password;
        this.actived = actived;
        this.provider = provider;
        this.providerId = providerId;
    }

    public Utilisateur(String email, String imageUrl, String password) {
        this.email = email;
        this.imageUrl = imageUrl;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public int getInsertUserId() {
        return insertUserId;
    }

    public void setInsertUserId(int insertUserId) {
        this.insertUserId = insertUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Date getDatePremiereConnexion() {
        return datePremiereConnexion;
    }

    public void setDatePremiereConnexion(Date datePremiereConnexion) {
        this.datePremiereConnexion = datePremiereConnexion;
    }

    public Date getDateDerniereConnexion() {
        return dateDerniereConnexion;
    }

    public void setDateDerniereConnexion(Date dateDerniereConnexion) {
        this.dateDerniereConnexion = dateDerniereConnexion;
    }

    public Date getDateChangementMdp() {
        return dateChangementMdp;
    }

    public void setDateChangementMdp(Date dateChangementMdp) {
        this.dateChangementMdp = dateChangementMdp;
    }

    public Integer getNbEssaiConnexion() {
        return nbEssaiConnexion;
    }

    public void setNbEssaiConnexion(Integer nbEssaiConnexion) {
        this.nbEssaiConnexion = nbEssaiConnexion;
    }

    public boolean isDoitChangerSonMdp() {
        return doitChangerSonMdp;
    }

    public void setDoitChangerSonMdp(boolean doitChangerSonMdp) {
        this.doitChangerSonMdp = doitChangerSonMdp;
    }

    public Date getDateDerniereDeconnexion() {
        return dateDerniereDeconnexion;
    }

    public void setDateDerniereDeconnexion(Date dateDerniereDeconnexion) {
        this.dateDerniereDeconnexion = dateDerniereDeconnexion;
    }

    public String getRaisonBlocage() {
        return raisonBlocage;
    }

    public void setRaisonBlocage(String raisonBlocage) {
        this.raisonBlocage = raisonBlocage;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
