package com.wechange.escourseservice.service.impl;

import com.wechange.easyschool.esmodel.entity.course.Course;
import com.wechange.easyschool.esmodel.entity.institution.Institution;
import com.wechange.escourseservice.repository.CourseRepository;
import com.wechange.escourseservice.service.CourseUpdateService;

import ch.qos.logback.classic.Logger;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class CourseUpdateServiceImpl implements CourseUpdateService {

    private CourseRepository courseRepository;

    public CourseUpdateServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(Course course) {
        course.setCreatedAt(new Date());
        return courseRepository.save(course);
    }
    
    public Course update(Course course) {
        return courseRepository.save(course);
    }
    
    public Course deleteCourse(String id) {
        Optional <Course> optional=courseRepository.findById(id);
        if(optional.isPresent()){
            Course course=optional.get();
            course.setDeleted(Boolean.TRUE);
            course.setDeletedAt(new Date());
            return courseRepository.save(course);
        }

        //institution.setIdCreator();
           return null;
    }
}
