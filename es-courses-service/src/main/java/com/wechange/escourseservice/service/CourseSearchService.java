package com.wechange.escourseservice.service;

import java.util.List;
import java.util.Optional;

import com.wechange.easyschool.esmodel.entity.course.Course;
public interface CourseSearchService {
	public Optional<Course> get(String id);
}
