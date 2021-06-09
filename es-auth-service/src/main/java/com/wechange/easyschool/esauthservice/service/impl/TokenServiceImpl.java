package com.wechange.easyschool.esauthservice.service.impl;

import com.wechange.easyschool.esauthservice.repository.TokenRepository;
import com.wechange.easyschool.esauthservice.service.TokenService;
import com.wechange.easyschool.escommon.exception.EnumErrorCode;
import com.wechange.easyschool.escommon.exception.CommonException;
import com.wechange.easyschool.esmodel.entity.user.EnumTokenStatus;
import com.wechange.easyschool.esmodel.entity.user.EnumTokenType;
import com.wechange.easyschool.esmodel.entity.user.Token;
import com.wechange.easyschool.esmodel.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${app.token.default.verification.duration}")
    private String defaultTokenExpiryDuration;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public Optional<Token> findByUser(User user) {
        return tokenRepository.findByUser(user);
    }

    @Override
    public String generateNewToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void verifyExpiration(Token token) throws CommonException {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            throw new CommonException(EnumErrorCode.ERROR_JWT_TOKEN_INVALID, token.getToken());
        }
    }

    @Override
    public Token updateExistingTokenWithStatusAndExpiry(Token existingToken) {
        existingToken.setTokenStatus(EnumTokenStatus.PENDING);
        existingToken.setExpiryDate(Instant.now().plusMillis(Long.valueOf(defaultTokenExpiryDuration)));
        return save(existingToken);
    }

    @Override
    public Token createTokenByType(User user, EnumTokenType tokenType, String tokenString, Long expiryDuration) {
        Token token = new Token();
        token.setToken(tokenString);
        token.setUser(user);
        token.setTokenStatus(EnumTokenStatus.PENDING);
        token.setTokenType(tokenType);
        token.setExpiryDate(Instant.now().plusMillis(expiryDuration));

        return tokenRepository.save(token);

    }

}
