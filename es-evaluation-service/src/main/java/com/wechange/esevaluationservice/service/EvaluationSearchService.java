package com.wechange.esevaluationservice.service;

import java.util.List;
import java.util.Optional;
import com.wechange.easyschool.esmodel.entity.Evaluation;
public interface EvaluationSearchService {
	public Optional<Evaluation> get(String id);
}
