package com.wechange.easyschool.esauthservice.service.impl;


import com.wechange.easyschool.esauthservice.repository.UserAuthentificationrRepository;
import com.wechange.easyschool.esauthservice.service.UserAuthentificationService;
import com.wechange.easyschool.escommon.exception.EnumErrorCode;
import com.wechange.easyschool.escommon.exception.DBItemNotFoundException;
import com.wechange.easyschool.escommon.repository.UserDetailsRepository;
import com.wechange.easyschool.escommon.security.CustomUserDetails;
import com.wechange.easyschool.escommon.security.JwtTokenProvider;
import com.wechange.easyschool.esmodel.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserAuthentificationServiceImpl implements UserAuthentificationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserAuthentificationrRepository userAuthentificationrRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @Override
    public Optional<Authentication> authenticateUser(String username, String password) {
            return Optional.ofNullable(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                    password)));
    }
    
    @Override
    public String createAndSaveUserRefreshToken(Authentication authentication) throws DBItemNotFoundException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String id = userDetails.getId();
        Optional<User> userOptional = userDetailsRepository.findById(id);
        userOptional.orElseThrow(() -> new DBItemNotFoundException(EnumErrorCode.ERROR_DB_ITEM_NOTFOUND,"User","id",id.toString()));
        User user = userOptional.get();

        String refreshToken = jwtTokenProvider.createRefreshToken(user);
        user.setRefreshToken(refreshToken);

        userAuthentificationrRepository.save(user);
        return refreshToken;

    }




}
