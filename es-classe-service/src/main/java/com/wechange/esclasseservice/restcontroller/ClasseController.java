package com.wechange.esclasseservice.restcontroller;

import com.wechange.easyschool.escommon.common.ResponseStatus;
import com.wechange.easyschool.escommon.common.RestResponse;
import com.wechange.easyschool.escommon.exception.DBItemNotFoundException;
import com.wechange.easyschool.escommon.exception.EnumErrorCode;
import com.wechange.easyschool.esmodel.entity.Classe;
import com.wechange.esclasseservice.service.ClasseRemoteService;
import com.wechange.esclasseservice.service.ClasseRetrieveService;
import com.wechange.esclasseservice.service.ClasseSearchService;
import com.wechange.esclasseservice.service.ClasseUpdateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 * @author Blondeau
 *
 */
/**
 * @author Blondeau
 *
 */
@RestController
@RequestMapping("/classe")
@CrossOrigin(origins= "http://localhost:4200")
@Api(value="Classe Management System", description="Operations pertaining to classe in Classe Management System")
public class ClasseController {
		
	    Logger logger = LoggerFactory.getLogger(ClasseController.class);
	    private ClasseUpdateService classeUpdateService;
	    private ClasseRetrieveService classeRetrieveService;
	    private ClasseRemoteService classeRemoteService;
	    private ClasseSearchService classeSearchService;

    public ClasseController(ClasseUpdateService classeUpdateService, ClasseRemoteService classeRemoteService, ClasseRetrieveService  classeRetrieveService, ClasseSearchService classeSearchService) {
        this.classeUpdateService = classeUpdateService;
        this.classeRetrieveService = classeRetrieveService;
        this.classeRemoteService=classeRemoteService;
        this.classeSearchService=classeSearchService;
    }
    
    
    
	@ApiOperation(value = "View a list of available classes", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping
	public ResponseEntity<List<Classe>> getAllClassesPage(
) {

try {
System.out.println("gettting All classes ...");
return ResponseEntity.ok(this.classeRetrieveService.getAll()) ;

} catch (Exception e) {
System.out.print("le message  :"+e.getMessage());
return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
}


}
	/**
	 * @param classe
	 * @return RestResponse
	 */
@ApiOperation(value = "View a list of available classes", response = List.class)
@PostMapping
public RestResponse add(@Valid @RequestBody Classe classe){
	System.out.println("ajout du id ");
		System.out.println("ajout du classe ");
   
        logger.debug("creation of a classe");
        Classe coursAdded= classeUpdateService.createClasse(classe);
        if (coursAdded == null)
        	return new RestResponse(classe, "Cours non crée", ResponseStatus.FAILED,HttpStatus.NO_CONTENT);

        	return new RestResponse(coursAdded, "Classe crée avec succès ", ResponseStatus.SUCCESS, HttpStatus.OK);
 }
    
/**
 * @param classe
 * @return
 */
@ApiOperation(value="updating a classe")
@PutMapping
public RestResponse  update(@Valid @RequestBody Classe classe) {
	String id=classe.getId();
	 if(id!=null) {
		 Optional<Classe> optional=this.classeRetrieveService.get(id);
		 if(!optional.isPresent()) 
			 return new RestResponse(classe, "class introuvable", ResponseStatus.FAILED,HttpStatus.NOT_FOUND);
		 		Classe classeDB=optional.get();
		 		classeDB.setName(classe.getName());
		 		
			/* if(classe.getChef()!=null) classeDB.setChef(classe.getChef()); */
		 		
		 		if(classe.getTitulaire()!=null) classeDB.setTitulaire(classe.getTitulaire());
		 	
		 		classeDB.setUpdatedAt(new Date());
		 		
		 		System.out.println("Updating courses .......");
		 		Classe courseUpdated=classeUpdateService.update(classeDB);
		        return     new RestResponse(courseUpdated, "cours crée", ResponseStatus.SUCCESS,HttpStatus.OK);   
	 }  
	 
	 return new RestResponse(classe, "Veiller spécifier l'identifiant du cours", ResponseStatus.FAILED,HttpStatus.BAD_REQUEST);  
	 		 		
}


    
    
	/**
	 * @param id
	 * @return Classe 
	 * @throws Exception
	 */
	@ApiOperation(value="get classe by id")
	@GetMapping("/{id}")
	public Classe get(@PathVariable String id) throws Exception {
		Optional<Classe> optional=this.classeSearchService.get(id);
	       if(!optional.isPresent()) 
	    	   throw  new DBItemNotFoundException(EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, "Le classe est introuvable",null,"Cours", "id", id+"") ;
	    	   return optional.get();
	}
	
    /**
     * supprime une cours
     * @param id 
     */
    @DeleteMapping("{id}")
    public RestResponse delete(@PathVariable("id") String id){
        logger.debug("Rest delete and classe ");
        System.out.println(" deleting classe :"+id);
        Classe classe=classeUpdateService.deleteClasse(id);
        if(classe!=null) {
        	return  new RestResponse(classe, "suppression reussie", ResponseStatus.SUCCESS, HttpStatus.OK);
        }
        
        	return new RestResponse(classe, "Cours introuvable", ResponseStatus.FAILED,HttpStatus.NOT_FOUND); 
    }
    
}
