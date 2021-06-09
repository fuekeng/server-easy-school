package com.wechange.esevaluationservice.service.impl;

import com.wechange.easyschool.esmodel.entity.Evaluation;
import com.wechange.easyschool.esmodel.entity.institution.Institution;
import com.wechange.esevaluationservice.repository.EvaluationRepository;
import com.wechange.esevaluationservice.service.EvaluationUpdateService;

import ch.qos.logback.classic.Logger;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluationUpdateServiceImpl implements EvaluationUpdateService {

    private EvaluationRepository evaluationRepository;

    public EvaluationUpdateServiceImpl(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public Evaluation createEvaluation(Evaluation evaluation ) {
        evaluation .setCreatedAt(new Date());
        return evaluationRepository.save(evaluation );
    }
    
    public Evaluation update(Evaluation evaluation ) {
        return evaluationRepository.save(evaluation );
    }
    
    public List<Evaluation> saveAll(List<Evaluation> list) {
    	return evaluationRepository.saveAll(list);
    }
    
    public Evaluation deleteEvaluation(String id) {
        Optional <Evaluation> optional=evaluationRepository.findById(id);
        if(optional.isPresent()){
            Evaluation evaluation =optional.get();
            evaluation .setDeleted(Boolean.TRUE);
            evaluation .setDeletedAt(new Date());
            return evaluationRepository.save(evaluation );
        }

        //institution.setIdCreator();
           return null;
    }
}
