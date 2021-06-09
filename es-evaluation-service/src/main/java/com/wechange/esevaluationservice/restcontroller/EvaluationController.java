package com.wechange.esevaluationservice.restcontroller;

import com.wechange.easyschool.escommon.common.ResponseStatus;
import com.wechange.easyschool.escommon.common.RestResponse;
import com.wechange.easyschool.escommon.exception.DBItemNotFoundException;
import com.wechange.easyschool.escommon.exception.EnumErrorCode;
import com.wechange.easyschool.esmodel.entity.Evaluation;
import com.wechange.esevaluationservice.service.EvaluationRemoteService;
import com.wechange.esevaluationservice.service.EvaluationRetrieveService;
import com.wechange.esevaluationservice.service.EvaluationSearchService;
import com.wechange.esevaluationservice.service.EvaluationUpdateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
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
@RestController
@RequestMapping("/evaluation")
@CrossOrigin(origins= "http://localhost:4200")
@Api(value="Evaluation Management System", description="Operations pertaining to evaluation in Evaluation Management System")
public class EvaluationController {
		
	    Logger logger = LoggerFactory.getLogger(EvaluationController.class);
	    private EvaluationUpdateService evaluationUpdateService;
	    private EvaluationRetrieveService evaluationRetrieveService;
	    private EvaluationRemoteService evaluationRemoteService;
	    private EvaluationSearchService evaluationSearchService;

    public EvaluationController(EvaluationUpdateService evaluationUpdateService, EvaluationRemoteService evaluationRemoteService, EvaluationRetrieveService  evaluationRetrieveService, EvaluationSearchService evaluationSearchService) {
        this.evaluationUpdateService = evaluationUpdateService;
        this.evaluationRetrieveService = evaluationRetrieveService;
        this.evaluationRemoteService=evaluationRemoteService;
        this.evaluationSearchService=evaluationSearchService;
    }
    
    
    
	@ApiOperation(value = "View a list of available evaluations", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping
	public ResponseEntity<List<Evaluation>> getAllEvaluationsPage(
) {

try {
System.out.println("gettting All evaluations ...");
return ResponseEntity.ok(this.evaluationRetrieveService.getAll()) ;

} catch (Exception e) {
System.out.print("le message  :"+e.getMessage());
return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
}


}
	/**
	 * @param evaluation
	 * @return RestResponse
	 */
@ApiOperation(value = "View a list of available evaluations", response = List.class)
@PostMapping
public RestResponse add(@Valid @RequestBody Evaluation evaluation){
		System.out.println("ajout du student "+evaluation.getStudent());
		System.out.println("ajout du coures "+evaluation.getCourse());
        logger.debug("creation of a evaluation");
        
        Evaluation evaluationAdded= evaluationUpdateService.createEvaluation(evaluation);
        if (evaluationAdded == null)
        	return new RestResponse(evaluation, "Cours non crée", ResponseStatus.FAILED,HttpStatus.NO_CONTENT);

        	return new RestResponse(evaluationAdded, "Evaluation crée avec succès ", ResponseStatus.SUCCESS, HttpStatus.OK);
 }


@PostMapping("/all")
public ResponseEntity<List<Evaluation>> addAll(@RequestBody List<Evaluation> list) {
	List<Evaluation> evaluations=evaluationUpdateService.saveAll(list);
	return new ResponseEntity<>(evaluations, HttpStatus.ACCEPTED);
}
    
/**
 * @param evaluation
 * @return
 */
@ApiOperation(value="updating a evaluation")
@PutMapping
public RestResponse  update(@Valid @RequestBody Evaluation evaluation) {
	 String id=evaluation.getId();
	 if(id!=null) {
		 Optional<Evaluation> optional=this.evaluationRetrieveService.get(id);
		 if(!optional.isPresent()) 
			 return new RestResponse(evaluation, "Cours introuvable", ResponseStatus.FAILED,HttpStatus.NOT_FOUND);
		 		Evaluation evaluationDB=optional.get();

		 		if(evaluation.getCourse()!=null) evaluationDB.setCourse(evaluation.getCourse());
		 		if(evaluation.getStudent()!=null) { evaluationDB.setStudent(evaluation.getStudent());}
		 		evaluationDB.setSeq1(evaluation.getSeq1());
		 		evaluationDB.setSeq2(evaluation.getSeq2());
		 		evaluationDB.setSeq3(evaluation.getSeq3());
		 		evaluationDB.setSeq4(evaluation.getSeq4());
		 		evaluationDB.setSeq5(evaluation.getSeq5());
		 		evaluationDB.setSeq6(evaluation.getSeq6());

		 		evaluationDB.setUpdatedAt(new Date());
		 		Evaluation evaluationUpdated=evaluationUpdateService.update(evaluationDB);
		        return     new RestResponse(evaluationUpdated, "evaluation crée", ResponseStatus.SUCCESS,HttpStatus.OK);   
	 }  
	 
	 return new RestResponse(evaluation, "Veiller spécifier l'identifiant du evaluation", ResponseStatus.FAILED,HttpStatus.BAD_REQUEST);  
	 		 		
}


    
@PutMapping("/all")
public void updateAll(@RequestBody List<Evaluation> list) {
	System.out.println("updating  all");
	
	try {
		for (Evaluation eval : list) {
			update(eval);
		} 
	} catch (Exception e) {
		// TODO: handle exception
	}
		
}
	/**
	 * @param id
	 * @return Evaluation 
	 * @throws Exception
	 */
	@ApiOperation(value="get evaluation by id")
	@GetMapping("/{id}")
	public Evaluation get(@PathVariable String id) throws Exception {
		Optional<Evaluation> optional=this.evaluationSearchService.get(id);
	       if(!optional.isPresent()) 
	    	   throw  new DBItemNotFoundException(EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, "Le evaluation est introuvable",null,"Cours", "id", id+"") ;
	    	   return optional.get();
	}
	
	
    /**
     * supprime une evaluation
     * @param id 
     */
    @DeleteMapping("{id}")
    public RestResponse delete(@PathVariable("id") String id){
        logger.debug("Rest delete and evaluation ");
        System.out.println(" deleting evaluation :"+id);
        Evaluation evaluation=evaluationUpdateService.deleteEvaluation(id);
        if(evaluation!=null) {
        	return  new RestResponse(evaluation, "suppression reussie", ResponseStatus.SUCCESS, HttpStatus.OK);
        }
        	return new RestResponse(evaluation, "Cours introuvable", ResponseStatus.FAILED,HttpStatus.NOT_FOUND); 
    }
    
}
