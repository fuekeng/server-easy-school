package com.wechange.esstudentservice.restcontroller;

import com.wechange.easyschool.escommon.common.ResponseStatus;
import com.wechange.easyschool.escommon.common.RestResponse;
import com.wechange.easyschool.escommon.exception.DBItemNotFoundException;
import com.wechange.easyschool.escommon.exception.EnumErrorCode;
import com.wechange.easyschool.esmodel.entity.Student;
import com.wechange.esstudentservice.service.StudentRemoteService;
import com.wechange.esstudentservice.service.StudentRetrieveService;
import com.wechange.esstudentservice.service.StudentSearchService;
import com.wechange.esstudentservice.service.StudentUpdateService;

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
/**
 * @author Blondeau
 *
 */
@RestController
@RequestMapping("/student")
@CrossOrigin(origins= "http://localhost:4200")
@Api(value="Student Management System", description="Operations pertaining to student in Student Management System")
public class StudentController {
		
	    Logger logger = LoggerFactory.getLogger(StudentController.class);
	    private StudentUpdateService studentUpdateService;
	    private StudentRetrieveService studentRetrieveService;
	    private StudentRemoteService studentRemoteService;
	    private StudentSearchService studentSearchService;

    public StudentController(StudentUpdateService studentUpdateService, StudentRemoteService studentRemoteService, StudentRetrieveService  studentRetrieveService, StudentSearchService studentSearchService) {
        this.studentUpdateService = studentUpdateService;
        this.studentRetrieveService = studentRetrieveService;
        this.studentRemoteService=studentRemoteService;
        this.studentSearchService=studentSearchService;
    }
    
    
    
	@ApiOperation(value = "View a list of available students", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudentsPage(
) {

try {
System.out.println("gettting All students ...");
return ResponseEntity.ok(this.studentRetrieveService.getAll()) ;

} catch (Exception e) {
System.out.print("le message  :"+e.getMessage());
return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
}


}
	/**
	 * @param student
	 * @return RestResponse
	 */
@ApiOperation(value = "View a list of available students", response = List.class)
@PostMapping
public RestResponse add(@Valid @RequestBody Student student){
		System.out.println("ajout du coures "+student.getFirstName());
    	student.setId(null);
        logger.debug("creation of a student");
        
        Student studentAdded= studentUpdateService.createStudent(student);
        if (studentAdded == null)
        	return new RestResponse(student, "Cours non crée", ResponseStatus.FAILED,HttpStatus.NO_CONTENT);

        	return new RestResponse(studentAdded, "Student crée avec succès ", ResponseStatus.SUCCESS, HttpStatus.OK);
 }
    
/**
 * @param student
 * @return
 */
@ApiOperation(value="updating a student")
@PutMapping
public RestResponse  update(@Valid @RequestBody Student student) {
	 String id=student.getId();
	 if(id!=null) {
		 Optional<Student> optional=this.studentRetrieveService.get(id);
		 if(!optional.isPresent()) 
			 return new RestResponse(student, "Cours introuvable", ResponseStatus.FAILED,HttpStatus.NOT_FOUND);
		 		Student studentDB=optional.get();
		 		studentDB.setFirstName(student.getFirstName());
		 		studentDB.setLastName(student.getLastName());
		 		studentDB.setSex(student.getSex());
		 		studentDB.setBithDate(student.getBithDate());
		 		studentDB.setPlaceOfBith(student.getPlaceOfBith());
		 		studentDB.setParentNumber1(student.getParentNumber1());
		 		studentDB.setParentNumber2(student.getParentNumber2());
		 		studentDB.setClasse(student.getClasse());

		 		studentDB.setUpdatedAt(new Date());
		 		Student studentUpdated=studentUpdateService.update(studentDB);
		        return     new RestResponse(studentUpdated, "student crée", ResponseStatus.SUCCESS,HttpStatus.OK);   
	 }  
	 
	 return new RestResponse(student, "Veiller spécifier l'identifiant du student", ResponseStatus.FAILED,HttpStatus.BAD_REQUEST);  
	 		 		
}


    
    
	/**
	 * @param id
	 * @return Student 
	 * @throws Exception
	 */
	@ApiOperation(value="get student by id")
	@GetMapping("/{id}")
	public Student get(@PathVariable String id) throws Exception {
		Optional<Student> optional=this.studentSearchService.get(id);
	       if(!optional.isPresent()) 
	    	   throw  new DBItemNotFoundException(EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, "Le student est introuvable",null,"Cours", "id", id+"") ;
	    	   return optional.get();
	}
	
    /**
     * supprime une student
     * @param id 
     */
    @DeleteMapping("{id}")
    public RestResponse delete(@PathVariable("id") String id){
        logger.debug("Rest delete and student ");
        System.out.println(" deleting student :"+id);
        Student student=studentUpdateService.deleteStudent(id);
        if(student!=null) {
        	return  new RestResponse(student, "suppression reussie", ResponseStatus.SUCCESS, HttpStatus.OK);
        }
        
        	return new RestResponse(student, "Cours introuvable", ResponseStatus.FAILED,HttpStatus.NOT_FOUND); 
    }
    
}
