package com.wechange.easyschool.esauthservice.restcontroller;


import com.wechange.easyschool.esauthservice.model.AuthResponse;
import com.wechange.easyschool.esauthservice.model.LoginRequest;
import com.wechange.easyschool.esauthservice.service.UserAuthentificationService;
import com.wechange.easyschool.escommon.common.ResponseStatus;
import com.wechange.easyschool.escommon.common.RestResponse;
import com.wechange.easyschool.escommon.security.CustomUserDetails;
import com.wechange.easyschool.escommon.security.JwtTokenProvider;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import java.util.Optional;
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins= "http://localhost:4200") 
public class AuthentificationController {


    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserAuthentificationService userAuthentificationService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public RestResponse home() {
        logger.info("Running home enpoint...");
        try {
            return new RestResponse("Services Keepknowing de gestion d'authentifications", "Login successfully !", ResponseStatus.SUCCESS, HttpStatus.OK);
        }catch(Exception e){
            return new RestResponse(e.getMessage(), com.wechange.easyschool.escommon.common.ResponseStatus.ABORTED, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/me")
    public RestResponse me() {
        logger.info("Running home enpoint...");
        try {
            CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
            return new RestResponse(customUserDetails.getUser(), "Login successfully !", ResponseStatus.SUCCESS, HttpStatus.OK);
        }catch(Exception e){
            return new RestResponse(e.getMessage(), ResponseStatus.ABORTED, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public RestResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        try {

            Optional<Authentication> authenticationOpt = userAuthentificationService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
            authenticationOpt.orElseThrow(() -> new Exception("Couldn't login user [" + loginRequest + "]"));
            Authentication authentication = authenticationOpt.get();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            String refreshToken = userAuthentificationService.createAndSaveUserRefreshToken(authentication);
            String jwtToken = jwtTokenProvider.createToken(authentication);
            logger.error("Le token :"+jwtToken);
            // Construire la réponse retour de l'authentification
            AuthResponse authResponse = new AuthResponse(jwtToken, refreshToken, jwtTokenProvider.getTokenExpiration(), customUserDetails.getUsername(), customUserDetails.getAuthorities());
            System.out.println(" la date ---------"+jwtTokenProvider.getTokenExpiration());
            return new RestResponse(authResponse, "Login successfully !", ResponseStatus.SUCCESS, HttpStatus.OK);
        }catch(Exception e){
            return new RestResponse(e.getMessage(), ResponseStatus.ABORTED, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/me")
    public RestResponse getCurrentUser() {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new RestResponse(userDetails, "Current user", ResponseStatus.SUCCESS, HttpStatus.OK);
    }
}
