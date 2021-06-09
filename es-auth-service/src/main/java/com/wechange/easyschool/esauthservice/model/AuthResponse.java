package com.wechange.easyschool.esauthservice.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long expiryDuration;
    private String username; // Informations supplémentaires lors de la connexion de l'utilisateur

    private Collection<? extends GrantedAuthority> authorities;

    public AuthResponse(String accessToken, String refreshToken, Long expiryDuration) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiryDuration = expiryDuration;
        this.tokenType = "Bearer ";
    }

    // Constructeur qui permet de retourner les informations supplémentaires de l'utilisateur connecté (en plus du username et des rôles)
    public AuthResponse(String accessToken, String refreshToken, Long expiryDuration, String username, Collection<? extends GrantedAuthority> authorities) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiryDuration = expiryDuration;
        this.tokenType = "Bearer ";

        // informations supplémentaires lors de la connexion d'un utilisateur
        this.username = username;
        this.authorities = authorities;
    }

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
    public AuthResponse(String accessToken, Long expiryDuration) {
        this.accessToken = accessToken;
        this.expiryDuration = expiryDuration;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getExpiryDuration() {
        return expiryDuration;
    }

    public void setExpiryDuration(Long expiryDuration) {
        this.expiryDuration = expiryDuration;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
    
   

}
