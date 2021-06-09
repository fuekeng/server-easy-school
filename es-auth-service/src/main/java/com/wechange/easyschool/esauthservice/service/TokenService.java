package com.wechange.easyschool.esauthservice.service;



import com.wechange.easyschool.escommon.exception.CommonException;
import com.wechange.easyschool.esmodel.entity.user.EnumTokenType;
import com.wechange.easyschool.esmodel.entity.user.Token;
import com.wechange.easyschool.esmodel.entity.user.User;

import java.util.Optional;

public interface TokenService {
     Token save(Token token);
     Optional<Token> findByToken(String token);
     Optional<Token> findByUser(User user);
     String generateNewToken();
     void verifyExpiration(Token token) throws CommonException;
     Token updateExistingTokenWithStatusAndExpiry(Token existingToken);
     Token createTokenByType(User user, EnumTokenType tokenType, String token, Long expiryDuration);

}
