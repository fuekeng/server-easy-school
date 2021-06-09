package com.wechange.esstudentservice.repository;

import com.wechange.easyschool.esmodel.entity.Student;
//import com.wechange.easyschool.esmodel.entity.user.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student,String>, PagingAndSortingRepository<Student,String> {
	Optional<Student> findById(String id);
	public List<Student> findByDeleted(boolean b);
	public Page<Student> findByFirstNameContainingIgnoreCase(String title, Pageable paging);
	boolean existsById(String id);
	public Page<Student> findByDeleted(Boolean deleted,Pageable paging);
	
}
