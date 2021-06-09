package com.wechange.escourseservice.service.impl;

import com.wechange.easyschool.esmodel.entity.course.Course;
import com.wechange.escourseservice.repository.CourseRepository;
import com.wechange.escourseservice.service.CourseRetrieveService;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseRetrieveServiceImpl implements CourseRetrieveService {

    private CourseRepository courseRepository;

    public CourseRetrieveServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

	public List<Course> getAll(){
		return this.courseRepository.findByDeleted(false);	
	}
	
	public Optional<Course> get(String id){
		return this.courseRepository.findById(id);			 
	}
	
	public Page<Course> findByIntituleContainingIgnoreCase(String title, Pageable paging){
		return this.courseRepository.findByIntituleContainingIgnoreCase(title, paging);
		 
	 }
	
	public Page<Course>  getAll(Pageable paging){
		return this.courseRepository.findAll(paging);
	}
	


	@Override
	public Page<Course> findByPageDeleted(Boolean deleted,Pageable paging) {
		// TODO Auto-generated method stub
		return this.courseRepository.findByDeleted(deleted,paging);
	}


}
