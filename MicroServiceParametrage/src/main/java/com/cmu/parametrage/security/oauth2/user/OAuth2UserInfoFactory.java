package com.cmu.parametrage.security.oauth2.user;

import com.cmu.parametrage.Exception.OAuth2AuthenticationProcessingException;
import com.cmu.parametrage.entities.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Pardon! Connectez-vous avec " + registrationId + "n'est pas encore supporté.");
        }
    }
}
