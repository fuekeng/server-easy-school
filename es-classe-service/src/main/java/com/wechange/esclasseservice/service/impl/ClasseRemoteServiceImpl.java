package com.wechange.esclasseservice.service.impl;
import org.springframework.stereotype.Service;

import com.wechange.esclasseservice.repository.ClasseRepository;
import com.wechange.esclasseservice.service.ClasseRemoteService;

@Service
public class ClasseRemoteServiceImpl implements ClasseRemoteService {

    private ClasseRepository courseRepository;

    public ClasseRemoteServiceImpl(ClasseRepository institutionRepository) {
        this.courseRepository = courseRepository;
    }

    
}
