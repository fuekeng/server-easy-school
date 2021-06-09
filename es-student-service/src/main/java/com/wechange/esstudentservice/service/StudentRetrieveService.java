package com.wechange.esstudentservice.service;

import com.wechange.easyschool.esmodel.entity.Student;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// tous ce qui recupère quelque chose de la bd
public interface StudentRetrieveService {
	 public List<Student> getAll();
	 public Optional<Student> get(String id);
	 public Page<Student> findByIntituleContainingIgnoreCase(String title, Pageable paging);
	 public Page<Student>  getAll(Pageable paging);
	 public Page<Student> findByPageDeleted(Boolean deleted,Pageable paging);
    
}
