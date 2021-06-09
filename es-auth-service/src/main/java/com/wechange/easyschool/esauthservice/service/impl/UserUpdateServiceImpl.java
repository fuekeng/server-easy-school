package com.wechange.easyschool.esauthservice.service.impl;

import com.wechange.easyschool.esauthservice.exception.EmailAlreadyUsedException;
import com.wechange.easyschool.esauthservice.exception.UsernameAlreadyUsedException;
import com.wechange.easyschool.esauthservice.repository.UserRepository;

import com.wechange.easyschool.esauthservice.service.TokenService;
import com.wechange.easyschool.esauthservice.service.UserRetrieveService;
import com.wechange.easyschool.esauthservice.service.UserUpdateService;
import com.wechange.easyschool.escommon.common.RestResponse;
import com.wechange.easyschool.escommon.exception.CommonException;
import com.wechange.easyschool.escommon.security.JwtTokenProvider;
import com.wechange.easyschool.escommon.util.RandomUtil;
import com.wechange.easyschool.esmodel.entity.course.Course;
import com.wechange.easyschool.esmodel.entity.user.EnumAuthority;
import com.wechange.easyschool.esmodel.entity.user.Token;
import com.wechange.easyschool.esmodel.entity.user.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserUpdateServiceImpl implements UserUpdateService {

    @Value("${app.token.refresh.duration}")
    private String refreshTokenExpiryDuration;

    @Value("${app.token.password.reset.duration}")
    private String resetPasswordTokenExpiryDuration;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRetrieveService userRetrieveService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    public User registerUser(User user, String password) {
        userRepository.findByUsernameOrPseudo(user.getUsername().toLowerCase(),user.getPseudo()).
                ifPresent(existingUser -> {
            boolean removed = removeNonActivatedUser(existingUser);
            if (!removed) {
                throw new UsernameAlreadyUsedException();
            }
        });
        userRepository.findOneByEmailIgnoreCase(user.getEmail()).ifPresent(existingUser -> {
            boolean removed = removeNonActivatedUser(existingUser);
            if (!removed) {
                throw new EmailAlreadyUsedException();
            }
        });
        User newUser = new User();
        if (user.getLangKey() == null)
            newUser.setLangKey("fr");
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setUsername(user.getUsername().toLowerCase());
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(user.getFirstName());
        newUser.setPseudo(user.getPseudo());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail().toLowerCase());
       /* newUser.setImageUrl(user.getImageUrl());
        newUser.setLangKey(user.getLangKey());*/
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        newUser.setRoles(user.getRoles());
        userRepository.save(newUser);
        //this.clearUserCaches(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }
    
    
    private boolean removeNonActivatedUser(User existingUser){
        if (existingUser.isActivated()) {
            return false;
        }
        userRepository.delete(existingUser);
        //this.clearUserCaches(existingUser);
        return true;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public Boolean emailAlreadyExists(String email) {
        return null;
    }

    @Override
    public String generateUserPseudo(String fullName) {
        return null;
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateFullName(String email, String fullName) {
        return Optional.empty();
    }

    @Override
    public User delete(String id) {
    	Optional <User> optional=userRepository.findById(id);
        if(optional.isPresent()){
            User user=optional.get();
            user.setDeleted(Boolean.TRUE);
            user.setDeletedAt(new Date());
            return userRepository.save(user);
        }
        
        return null;

    }

    @Override
    public Optional<User> updateQualification(String email, String qualification) {
        return Optional.empty();
    }

    @Override
    public Optional<User> updateBirthDate(String email, Date birthDate) {
        return Optional.empty();
    }

    @Override
    public Optional<User> updateAddress(String email, String address) {
        return Optional.empty();
    }

    @Override
    public Optional<User> updateDescription(String email, String description) {
        return Optional.empty();
    }

    @Override
    public Optional<User> processEmailVerificattion(String token) throws CommonException {
        return Optional.empty();
    }

    @Override
    public Optional<Token> createAndSaveUserRefreshToken(Authentication authentication) {
        return Optional.empty();
    }

    @Override
    public Optional<Token> generatePasswordResetToken(String email) {
        return Optional.empty();
    }

    @Override
    public Boolean currentPasswordMatches(User currentUser, String password) {
        return null;
    }

    @Override
    public Optional<User> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return userRepository.findOneByActivationKey(key)
                .map(user -> {
                    // activate given user for the registration key.
                    user.setActivated(true);
                    user.setActivationKey(null);
                    userRepository.save(user);
                  //  this.clearUserCaches(user);
                    log.debug("Activated user: {}", user);
                    return user;
                });
    }

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param firstName first name of user.
     * @param lastName  last name of user.
     * @param email     email id of user.
     * @param langKey   language key.
     * @param imageUrl  image URL of user.
     */

    public void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserName = userDetails.getUsername();
       userRepository.findByUsernameOrEmail(currentUserName,currentUserName)
                .ifPresent(user -> {
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email.toLowerCase());
                    user.setLangKey(langKey);
                    user.setImageUrl(imageUrl);
                    userRepository.save(user);
                    //this.clearUserCaches(user);
                    log.debug("Changed Information for User: {}", user);
                });
    }

}
