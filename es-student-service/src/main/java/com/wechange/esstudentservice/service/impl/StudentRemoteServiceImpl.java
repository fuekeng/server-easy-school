package com.wechange.esstudentservice.service.impl;
import org.springframework.stereotype.Service;

import com.wechange.esstudentservice.repository.StudentRepository;
import com.wechange.esstudentservice.service.StudentRemoteService;

@Service
public class StudentRemoteServiceImpl implements StudentRemoteService {

    private StudentRepository studentRepository;

    public StudentRemoteServiceImpl(StudentRepository institutionRepository) {
        this.studentRepository = studentRepository;
    }

    
}
