package com.wechange.easyschool.esauthservice.restcontroller;

import com.wechange.easyschool.esauthservice.exception.EmailAlreadyUsedException;
import com.wechange.easyschool.esauthservice.exception.InvalidPasswordException;
import com.wechange.easyschool.esauthservice.restcontroller.vm.ManagedUserVM;

import com.wechange.easyschool.esauthservice.service.MailService;
import com.wechange.easyschool.esauthservice.service.TokenService;
import com.wechange.easyschool.esauthservice.service.UserRetrieveService;
import com.wechange.easyschool.esauthservice.service.UserUpdateService;
import com.wechange.easyschool.escommon.common.ResponseStatus;
import com.wechange.easyschool.escommon.common.RestResponse;
import com.wechange.easyschool.escommon.common.annotation.CurrentUser;
import com.wechange.easyschool.escommon.exception.DBItemNotFoundException;
import com.wechange.easyschool.escommon.exception.EnumErrorCode;
import com.wechange.easyschool.escommon.security.CustomUserDetails;
import com.wechange.easyschool.esmodel.entity.user.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/account")
public class AccountController {

    @Value("${app.storage.root.directory}")
    private String storageRootDirectory;

    @Value("${app.storage.algorithm}")
    private String algorithm;

    @Value("${app.token.email.verification.duration}")
    private String emailVerificationTokenExpiryDuration;

    private  final TokenService tokenService;
    private final UserRetrieveService userRetrieveService;
    private final UserUpdateService userUpdateService;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;


    private final Logger logger = LoggerFactory.getLogger(getClass());

    public AccountController(TokenService tokenService, UserRetrieveService userRetrieveService, UserUpdateService userUpdateService,
                             PasswordEncoder passwordEncoder, MailService mailService) {
        this.tokenService = tokenService;
        this.userRetrieveService = userRetrieveService;
        this.userUpdateService = userUpdateService;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @PostMapping("/register")
    public RestResponse registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
        logger.debug("Rest request to register user",managedUserVM);
        if (!checkPasswordLength(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }
        User user = userUpdateService.registerUser(managedUserVM, managedUserVM.getPassword());
        mailService.sendActivationEmail(user);
        return  new RestResponse(null,"account created successfully", ResponseStatus.SUCCESS,HttpStatus.CREATED);
    }

    @PostMapping("/verifyEmail")
    public RestResponse verifyEmail(@RequestParam("token") String token) {
    //TODO
        return null;
    }

    /**
     * {@code GET  /activate} : activate the registered user.
     *
     * @param key the activation key.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be activated.
     */
    @GetMapping("/activate")
    public RestResponse activateAccount(@RequestParam(value = "key") String key) throws DBItemNotFoundException {
        logger.debug("request to activate user with key {}",key);
        Optional<User> user = userUpdateService.activateRegistration(key);
        if (!user.isPresent()) {
            throw new DBItemNotFoundException(EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, "User","activation key",key);
        }
        return new RestResponse("user activated successfully",ResponseStatus.SUCCESS,HttpStatus.OK);
    }


    /*@PostMapping("/account")
    public void saveAccount(@Valid @RequestBody User user) throws DBItemNotFoundException {
        Optional<User> existingUser = userRetrieveService.findByEmailIgnoreCase(user.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getUsername().equalsIgnoreCase(userDetails.getUsername()))) {
            throw new EmailAlreadyUsedException();
        }
        Optional<User> existing = userRetrieveService.findByUsernameOrPseudo(userDetails.getUsername(),user.getPseudo());
        if (!existing.isPresent()) {
            throw new DBItemNotFoundException(EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, "User","username",user.getUsername());
        }
        userUpdateService.updateUser(user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getLangKey(), user.getImageUrl());
    }*/

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
                password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
                password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }
}
