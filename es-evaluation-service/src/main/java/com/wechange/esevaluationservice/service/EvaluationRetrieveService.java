package com.wechange.esevaluationservice.service;

import com.wechange.easyschool.esmodel.entity.Evaluation;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// tous ce qui recupère quelque chose de la bd
public interface EvaluationRetrieveService {
	 public List<Evaluation> getAll();
	 public Optional<Evaluation> get(String id);
	 public Page<Evaluation> findByIntituleContainingIgnoreCase(String title, Pageable paging);
	 public Page<Evaluation>  getAll(Pageable paging);
	 public Page<Evaluation> findByPageDeleted(Boolean deleted,Pageable paging);
    
}
