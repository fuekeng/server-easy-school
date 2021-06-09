package com.wechange.esstudentservice.service.impl;

import com.wechange.easyschool.esmodel.entity.Student;
import com.wechange.esstudentservice.repository.StudentRepository;
import com.wechange.esstudentservice.service.StudentRetrieveService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentRetrieveServiceImpl implements StudentRetrieveService {

    private StudentRepository studentRepository;

    public StudentRetrieveServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

	public List<Student> getAll(){
		return this.studentRepository.findByDeleted(false);	
	}
	
	public Optional<Student> get(String id){
		return this.studentRepository.findById(id);			 
	}
	
	public Page<Student> findByIntituleContainingIgnoreCase(String title, Pageable paging){
		return this.studentRepository.findByFirstNameContainingIgnoreCase(title, paging);
		 
	 }
	
	public Page<Student>  getAll(Pageable paging){
		return this.studentRepository.findAll(paging);
	}
	


	@Override
	public Page<Student> findByPageDeleted(Boolean deleted,Pageable paging) {
		// TODO Auto-generated method stub
		return this.studentRepository.findByDeleted(deleted,paging);
	}


}
