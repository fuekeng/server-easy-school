package com.wechange.escourseservice.service.impl;
import org.springframework.stereotype.Service;

import com.wechange.escourseservice.repository.CourseRepository;
import com.wechange.escourseservice.service.CourseRemoteService;

@Service
public class CourseRemoteServiceImpl implements CourseRemoteService {

    private CourseRepository courseRepository;

    public CourseRemoteServiceImpl(CourseRepository institutionRepository) {
        this.courseRepository = courseRepository;
    }

    
}
