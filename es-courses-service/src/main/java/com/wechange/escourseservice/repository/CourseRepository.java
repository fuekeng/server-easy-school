package com.wechange.escourseservice.repository;

import com.wechange.easyschool.esmodel.entity.course.Course;
import com.wechange.easyschool.esmodel.entity.user.User;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<Course,String>, PagingAndSortingRepository<Course,String> {
	Optional<Course> findById(String id);
	public List<Course> findByDeleted(boolean b);
	public Page<Course> findByIntituleContainingIgnoreCase(String title, Pageable paging);
	boolean existsById(String id);
	public Page<Course> findByDeleted(Boolean deleted,Pageable paging);
	
}
