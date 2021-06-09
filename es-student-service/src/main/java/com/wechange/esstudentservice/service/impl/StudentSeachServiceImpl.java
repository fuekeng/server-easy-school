package com.wechange.esstudentservice.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.wechange.esstudentservice.repository.StudentRepository;
import com.wechange.esstudentservice.service.StudentSearchService;
import com.wechange.easyschool.esmodel.entity.Student;

@Service
public class StudentSeachServiceImpl implements StudentSearchService {

    private StudentRepository studentRepository;

	public Optional<Student> get(String id){
		return this.studentRepository.findById(id);
	}
	

}
