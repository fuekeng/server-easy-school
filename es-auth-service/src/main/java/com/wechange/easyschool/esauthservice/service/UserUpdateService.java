package com.wechange.easyschool.esauthservice.service;


import com.wechange.easyschool.escommon.common.RestResponse;
import com.wechange.easyschool.escommon.exception.CommonException;
import com.wechange.easyschool.esmodel.entity.user.Token;
import com.wechange.easyschool.esmodel.entity.user.User;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.Optional;

public interface UserUpdateService {

    User save(User user);
    Boolean emailAlreadyExists(String email);
    String generateUserPseudo(String fullName);
    User registerUser(User user, String password);
    User update(User user);
    Optional<User> updateFullName(String email, String fullName);
    User delete(String id);
    //Optional<User> updatePassword(User user, UpdatePasswordRequest updatePasswordRequest);
    Optional<User> updateQualification(String email, String qualification);
    Optional<User> updateBirthDate(String email, Date birthDate);
    Optional<User> updateAddress(String email, String address);
    Optional<User> updateDescription(String email, String description);
    Optional<User> processEmailVerificattion(String token) throws CommonException;
    Optional<Token> createAndSaveUserRefreshToken(Authentication authentication);
    Optional<Token> generatePasswordResetToken(String email);
   // Optional<User> resetPassword(PasswordResetRequest passwordResetRequest) throws KKCommonException;
    Boolean currentPasswordMatches(User currentUser, String password);

    Optional<User> activateRegistration(String key);

    void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl);
}
