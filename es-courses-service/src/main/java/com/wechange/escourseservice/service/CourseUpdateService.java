// on met tous ce qui modifie la bd
package com.wechange.escourseservice.service;

import com.wechange.easyschool.esmodel.entity.course.Course;

public interface CourseUpdateService {
    Course createCourse(Course course);
    Course deleteCourse(String id);
	Course update(Course coursDB);
}
