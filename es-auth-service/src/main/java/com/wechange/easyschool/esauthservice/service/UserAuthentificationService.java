package com.wechange.easyschool.esauthservice.service;

import com.wechange.easyschool.escommon.exception.DBItemNotFoundException;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface UserAuthentificationService {

     Optional<Authentication> authenticateUser(String username, String password);
     String createAndSaveUserRefreshToken(Authentication authentication) throws DBItemNotFoundException;

}
