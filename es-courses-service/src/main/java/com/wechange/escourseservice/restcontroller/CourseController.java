package com.wechange.escourseservice.restcontroller;

import com.wechange.easyschool.escommon.common.ResponseStatus;
import com.wechange.easyschool.escommon.common.RestResponse;
import com.wechange.easyschool.escommon.exception.DBItemNotFoundException;
import com.wechange.easyschool.escommon.exception.EnumErrorCode;
import com.wechange.easyschool.esmodel.entity.course.Course;
import com.wechange.escourseservice.service.CourseRemoteService;
import com.wechange.escourseservice.service.CourseRetrieveService;
import com.wechange.escourseservice.service.CourseSearchService;
import com.wechange.escourseservice.service.CourseUpdateService;
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
@RequestMapping("/course")
@CrossOrigin(origins= "http://localhost:4200")
@Api(value="Course Management System", description="Operations pertaining to course in Course Management System")
public class CourseController {
		
	    Logger logger = LoggerFactory.getLogger(CourseController.class);
	    private CourseUpdateService courseUpdateService;
	    private CourseRetrieveService courseRetrieveService;
	    private CourseRemoteService courseRemoteService;
	    private CourseSearchService courseSearchService;

    public CourseController(CourseUpdateService courseUpdateService, CourseRemoteService courseRemoteService, CourseRetrieveService  courseRetrieveService, CourseSearchService courseSearchService) {
        this.courseUpdateService = courseUpdateService;
        this.courseRetrieveService = courseRetrieveService;
        this.courseRemoteService=courseRemoteService;
        this.courseSearchService=courseSearchService;
    }
    
    
    
	@ApiOperation(value = "View a list of available courses", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping
	public ResponseEntity<List<Course>> getAllCoursesPage(
) {

try {
System.out.println("gettting All courses ...");
return ResponseEntity.ok(this.courseRetrieveService.getAll()) ;

} catch (Exception e) {
System.out.print("le message  :"+e.getMessage());
return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
}


}
	/**
	 * @param course
	 * @return RestResponse
	 */
@ApiOperation(value = "View a list of available courses", response = List.class)
@PostMapping
public RestResponse add(@Valid @RequestBody Course course){
		System.out.println("ajout du coures "+course.getIntitule());
        logger.debug("creation of a course");
        
        Course coursAdded= courseUpdateService.createCourse(course);
        if (coursAdded == null)
        	return new RestResponse(course, "Cours non crée", ResponseStatus.FAILED,HttpStatus.NO_CONTENT);

        	return new RestResponse(coursAdded, "Course crée avec succès ", ResponseStatus.SUCCESS, HttpStatus.OK);
 }
    
/**
 * @param course
 * @return
 */
@ApiOperation(value="updating a course")
@PutMapping
public RestResponse  update(@Valid @RequestBody Course course) {
	 String id=course.getId();
	 if(id!=null) {
		 Optional<Course> optional=this.courseRetrieveService.get(id);
		 if(!optional.isPresent()) 
			 return new RestResponse(course, "Cours introuvable", ResponseStatus.FAILED,HttpStatus.NOT_FOUND);
		 		Course coursDB=optional.get();
		 		coursDB.setCode(course.getCode());
		 		coursDB.setClasse(course.getClasse());
		 		coursDB.setCoef(course.getCoef());
		 		coursDB.setDepartement(course.getDepartement());
		 		coursDB.setEnseignant(course.getEnseignant());
		 		coursDB.setIntitule(course.getIntitule());
		 		coursDB.setNombre_heures_prevues(course.getNombre_heures_prevues());
		 		coursDB.setNombre_heures_faire(course.getNombre_heures_faire());
		 		coursDB.setNombre_lecons_prevues(course.getNombre_heures_prevues());
		 		coursDB.setNombre_lecons_faites(course.getNombre_heures_faire());

		 		coursDB.setUpdatedAt(new Date());
		 		Course coursUpdated=courseUpdateService.update(coursDB);
		        return     new RestResponse(coursUpdated, "cours crée", ResponseStatus.SUCCESS,HttpStatus.OK);   
	 }  
	 
	 return new RestResponse(course, "Veiller spécifier l'identifiant du cours", ResponseStatus.FAILED,HttpStatus.BAD_REQUEST);  
	 		 		
}


    
    
	/**
	 * @param id
	 * @return Course 
	 * @throws Exception
	 */
	@ApiOperation(value="get course by id")
	@GetMapping("/{id}")
	public Course get(@PathVariable String id) throws Exception {
		Optional<Course> optional=this.courseSearchService.get(id);
	       if(!optional.isPresent()) 
	    	   throw  new DBItemNotFoundException(EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, "Le course est introuvable",null,"Cours", "id", id+"") ;
	    	   return optional.get();
	}
	
    /**
     * supprime une cours
     * @param id 
     */
    @DeleteMapping("{id}")
    public RestResponse delete(@PathVariable("id") String id){
        logger.debug("Rest delete and course ");
        System.out.println(" deleting course :"+id);
        Course course=courseUpdateService.deleteCourse(id);
        if(course!=null) {
        	return  new RestResponse(course, "suppression reussie", ResponseStatus.SUCCESS, HttpStatus.OK);
        }
        
        	return new RestResponse(course, "Cours introuvable", ResponseStatus.FAILED,HttpStatus.NOT_FOUND); 
    }
    
}
