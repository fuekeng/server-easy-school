package com.wechange.easyschool.escommon.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Component
@PropertySource(value={"application-dev.yml"})
public class JwtConfig {
    @Value("${app.security.jwt.loginUrl}")
    private String loginUrl;

    @Value("${app.security.jwt.header}")
    private String header;

    @Value("${app.security.jwt.prefix}")
    private String prefix;

    @Value("${app.security.jwt.expiration}")
    private long expiration;

    @Value("${app.security.jwt.refresh.expiration}")
    private long refreshExpiration;

    @Value("${app.security.jwt.secret}")
    private String secret;
    
    
	public JwtConfig() {
		super();
	}
	
	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public long getExpiration() {
		return expiration;
	}

	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}

	public long getRefreshExpiration() {
		return refreshExpiration;
	}

	public void setRefreshExpiration(long refreshExpiration) {
		this.refreshExpiration = refreshExpiration;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
    
    

}
