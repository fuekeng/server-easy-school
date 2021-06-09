package com.wechange.escourseservice.service;

import com.wechange.easyschool.esmodel.entity.course.Course;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// tous ce qui recupère quelque chose de la bd
public interface CourseRetrieveService {
	 public List<Course> getAll();
	 public Optional<Course> get(String id);
	 public Page<Course> findByIntituleContainingIgnoreCase(String title, Pageable paging);
	 public Page<Course>  getAll(Pageable paging);
	 public Page<Course> findByPageDeleted(Boolean deleted,Pageable paging);
    
}
