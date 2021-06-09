package com.wechange.esevaluationservice.service.impl;
import org.springframework.stereotype.Service;

import com.wechange.esevaluationservice.repository.EvaluationRepository;
import com.wechange.esevaluationservice.service.EvaluationRemoteService;

@Service
public class EvaluationRemoteServiceImpl implements EvaluationRemoteService {

    private EvaluationRepository studentRepository;

    public EvaluationRemoteServiceImpl(EvaluationRepository institutionRepository) {
        this.studentRepository = studentRepository;
    }

    
}
