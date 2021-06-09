package com.wechange.easyschool.escommon.security;
import com.wechange.easyschool.escommon.exception.EnumErrorCode;
import com.wechange.easyschool.escommon.exception.CommonException;
import com.wechange.easyschool.esmodel.entity.user.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Autowired
    private JwtConfig config;


    public String createToken(Authentication authentication) {
        CustomUserDetails  customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Date expiryDate = new Date(new Date().getTime() + config.getExpiration());
        
        return Jwts.builder()
                .setSubject(customUserDetails.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, config.getSecret())
                .compact();
    }

    public String createRefreshToken(User user) {
        Date expiryDate = new Date(new Date().getTime() + config.getRefreshExpiration());
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, user.getPassword())
                .setSubject(user.getId().toString())
                .setExpiration(expiryDate)
                .setIssuedAt(new Date())
                .compact();
    }

    public  long getTokenExpiration()
    {
        return config.getExpiration();
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(config.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(config.getHeader());

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(config.getPrefix())) {
            return bearerToken.substring(config.getPrefix().length()+1, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String authToken) throws CommonException {
        try {
            Jwts.parser().setSigningKey(config.getSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
            throw  new CommonException(EnumErrorCode.ERROR_JWT_SIGNATURE_INVALID,ex);
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
            logger.error("Invalid JWT token."+"Message: "+ex.getMessage() +"Cause :"+ex.getLocalizedMessage());
            throw  new CommonException(EnumErrorCode.ERROR_JWT_TOKEN_INVALID,ex);
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
            throw  new CommonException(EnumErrorCode.ERROR_JWT_TOKEN_EXPIRED,ex);
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
            throw  new CommonException(EnumErrorCode.ERROR_JWT_TOKEN_UNSUPPORTED,ex);
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
            throw  new CommonException(EnumErrorCode.ERROR_JWT_TOKEN_NOTFOUND,ex);
        }
        catch (Exception ex) {
            logger.error("Invalid JWT token."+"Message: "+ex.getMessage() +"Cause :"+ex.getCause());
            throw  new CommonException(EnumErrorCode.ERROR_JWT_TOKEN_INVALID,ex);
        }
    }
}