package com.wechange.easyschool.esauthservice.restcontroller;


import com.wechange.easyschool.esauthservice.service.TokenService;
import com.wechange.easyschool.esauthservice.service.UserRetrieveService;
import com.wechange.easyschool.esauthservice.service.UserUpdateService;
import com.wechange.easyschool.escommon.common.ResponseStatus;
import com.wechange.easyschool.escommon.common.RestResponse;
import com.wechange.easyschool.escommon.common.annotation.CurrentUser;
import com.wechange.easyschool.escommon.exception.DBItemNotFoundException;
import com.wechange.easyschool.escommon.exception.EnumErrorCode;
import com.wechange.easyschool.escommon.security.CustomUserDetails;
import com.wechange.easyschool.escommon.security.JwtTokenProvider;
import com.wechange.easyschool.esmodel.entity.course.Course;
import com.wechange.easyschool.esmodel.entity.user.EnumAuthority;
import com.wechange.easyschool.esmodel.entity.user.User;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins= "http://localhost:4200")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
    @Value("${app.storage.root.directory}")
    private String storageRootDirectory;

    @Value("${app.storage.algorithm}")
    private String algorithm;

    @Value("${app.token.email.verification.duration}")
    private String emailVerificationTokenExpiryDuration;


    TokenService tokenService;


    private UserRetrieveService userRetrieveService;


    private UserUpdateService userUpdateService;

    private PasswordEncoder passwordEncoder;

    JwtTokenProvider jwtTokenProvider;

    public UserController(TokenService tokenService, UserRetrieveService userRetrieveService, UserUpdateService userUpdateService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.tokenService = tokenService;
        this.userRetrieveService = userRetrieveService;
        this.userUpdateService = userUpdateService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Accès aux informations de l'utilisateur courant connecté
    @GetMapping("/me")
    public RestResponse getCurrentUser(@CurrentUser CustomUserDetails customUserDetails) {
        return new RestResponse(customUserDetails.getUser(), "Current user", ResponseStatus.SUCCESS, HttpStatus.OK);
    }
    
    @PostMapping
    public RestResponse create(@Valid @RequestBody User user){
    	System.out.println("ajout d'un utilisateur");
    	System.out.println("ajout d'un utilisateur mot de passe"+user.getPassword());
        logger.debug("creation of a user");
    	user.setId(null);
    	user.addRole(EnumAuthority.USER);
        User userAdded= userUpdateService.registerUser(user, user.getPassword());
        if (userAdded == null)
        	return new RestResponse(userAdded, "Utilisateur  non crée", ResponseStatus.FAILED,HttpStatus.NO_CONTENT);

        	return new RestResponse(userAdded, "Utilisateur crée avec succès ", ResponseStatus.SUCCESS, HttpStatus.OK);
    }
    
    
  
    
    @PutMapping
	public RestResponse update(@Valid @RequestBody User user) {
    	logger.error("", user);
		 String id=user.getId();
		 if(id!=null) {
			 Optional<User> optional=this.userRetrieveService.get(id);
			 if(!optional.isPresent()) 
		        	return new RestResponse(null, "Cours non crée", ResponseStatus.FAILED,HttpStatus.NOT_FOUND);
			 
			 		User userDB=optional.get();
			 		userDB.setPseudo(user.getPseudo());
			 		userDB.setEmail(user.getEmail());
			 		userDB.setFirstName(user.getFirstName());
			 		userDB.setImageUrl(user.getImageUrl());
			 		userDB.setLastName(user.getLastName());
			 		userDB.setUsername(user.getUsername());
			 		User userUpdated=userUpdateService.update(userDB);
			       return new RestResponse(userUpdated, "Cours non crée", ResponseStatus.SUCCESS,HttpStatus.OK);
        
		 }  
		 
		 return new RestResponse(null, "un cours existant doit avoir un identifiant", ResponseStatus.SUCCESS,HttpStatus.OK); 		
	}



	@ApiOperation(value = "View a list of available users", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllUserPage(@RequestParam(required = false) String firstName,
																 @RequestParam(defaultValue = "0") int page,
																 @RequestParam(defaultValue = "3") int size
			) {
		
		try {
			System.out.println("gettting All users ...");
			//return ResponseEntity.ok(this.courseRetrieveService.getAll()) ;
			ResponseEntity<Map<String, Object>>responseEntity=new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		
				List<User> users = new ArrayList<User>();
				Pageable paging=PageRequest.of(page, size);
				Page<User> pageUser;
				
				
		      if (firstName== null)
		    	  pageUser=this.userRetrieveService.getAll(paging);
		      else 
		    	  pageUser=this.userRetrieveService.findByFirstNameContainingIgnoreCase(firstName, paging);
		      
			      users=pageUser.getContent();
			      Map<String, Object> response= new HashMap();
			      response.put("users",users);
			      response.put("currentPage",pageUser.getNumber());
			      response.put("totalItems", pageUser.getTotalElements());
			      response.put("totalPages",pageUser.getTotalPages());
			      
			      return responseEntity= new ResponseEntity<>(response, HttpStatus.OK);
		    } catch (Exception e) {
		    	System.out.print("le message  :"+e.getMessage());
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		
	
	}
    
	@GetMapping("/{id}")
	public User get(@PathVariable String id) throws Exception {
		Optional<User> optional=this.userRetrieveService.get(id);
	       if(!optional.isPresent()) 
	    	   throw  new DBItemNotFoundException(EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, "Le course est introuvable",null,"User", "id", id+"") ;

	    	   return optional.get();
	   	}
	
    /**
     * supprime un user
     * @param id
     */
	
	  @DeleteMapping("{id}") 
	  public RestResponse delete(@PathVariable("id") String id){ 
			  logger.debug("Rest delete and user ");
		  System.out.println(" deleting user :"+id); User
		  user=userUpdateService.delete(id);
		  if(user!=null) { 
			  return new RestResponse(user, "suppression reussie", ResponseStatus.SUCCESS, HttpStatus.OK); }
		  
		  
		  return new RestResponse(user, "suppression impossible", ResponseStatus.FAILED, HttpStatus.NOT_MODIFIED);
	  
	  }
	 

}
