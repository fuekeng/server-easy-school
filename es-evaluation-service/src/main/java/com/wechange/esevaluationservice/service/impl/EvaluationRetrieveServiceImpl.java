package com.wechange.esevaluationservice.service.impl;

import com.wechange.easyschool.esmodel.entity.Evaluation;
import com.wechange.esevaluationservice.repository.EvaluationRepository;
import com.wechange.esevaluationservice.service.EvaluationRetrieveService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationRetrieveServiceImpl implements EvaluationRetrieveService {

    private EvaluationRepository evaluationRepository;

    public EvaluationRetrieveServiceImpl(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

	public List<Evaluation> getAll(){
		return this.evaluationRepository.findByDeleted(false);	
	}
	
	public Optional<Evaluation> get(String id){
		return this.evaluationRepository.findById(id);			 
	}
	
	public Page<Evaluation> findByIntituleContainingIgnoreCase(String title, Pageable paging){
		return this.evaluationRepository.findByStudentContainingIgnoreCase(title, paging);
		 
	 }
	
	public Page<Evaluation>  getAll(Pageable paging){
		return this.evaluationRepository.findAll(paging);
	}
	


	@Override
	public Page<Evaluation> findByPageDeleted(Boolean deleted,Pageable paging) {
		// TODO Auto-generated method stub
		return this.evaluationRepository.findByDeleted(deleted,paging);
	}


}
