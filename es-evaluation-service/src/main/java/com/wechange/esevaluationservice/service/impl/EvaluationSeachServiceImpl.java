package com.wechange.esevaluationservice.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.wechange.easyschool.esmodel.entity.Evaluation;
import com.wechange.esevaluationservice.repository.EvaluationRepository;
import com.wechange.esevaluationservice.service.EvaluationSearchService;

@Service
public class EvaluationSeachServiceImpl implements EvaluationSearchService {

    private EvaluationRepository evaluationRepository;

	public Optional<Evaluation> get(String id){
		return this.evaluationRepository.findById(id);
	}
	

}
