package com.wechange.escourseservice.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.wechange.escourseservice.repository.CourseRepository;
import com.wechange.escourseservice.service.CourseSearchService;
import com.wechange.easyschool.esmodel.entity.course.Course;

@Service
public class CourseSeachServiceImpl implements CourseSearchService {

    private CourseRepository courseRepository;

	public Optional<Course> get(String id){
		return this.courseRepository.findById(id);
	}
	

}
