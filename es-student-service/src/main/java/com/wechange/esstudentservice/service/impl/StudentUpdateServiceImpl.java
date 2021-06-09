package com.wechange.esstudentservice.service.impl;

import com.wechange.easyschool.esmodel.entity.Student;
import com.wechange.easyschool.esmodel.entity.institution.Institution;
import com.wechange.esstudentservice.repository.StudentRepository;
import com.wechange.esstudentservice.service.StudentUpdateService;

import ch.qos.logback.classic.Logger;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class StudentUpdateServiceImpl implements StudentUpdateService {

    private StudentRepository studentRepository;

    public StudentUpdateServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student ) {
        student .setCreatedAt(new Date());
        return studentRepository.save(student );
    }
    
    public Student update(Student student ) {
        return studentRepository.save(student );
    }
    
    public Student deleteStudent(String id) {
        Optional <Student> optional=studentRepository.findById(id);
        if(optional.isPresent()){
            Student student =optional.get();
            student .setDeleted(Boolean.TRUE);
            student .setDeletedAt(new Date());
            return studentRepository.save(student );
        }

        //institution.setIdCreator();
           return null;
    }
}
