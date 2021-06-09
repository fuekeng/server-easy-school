// on met tous ce qui modifie la bd
package com.wechange.esevaluationservice.service;

import java.util.List;

import com.wechange.easyschool.esmodel.entity.Evaluation;

public interface EvaluationUpdateService {
    Evaluation createEvaluation(Evaluation student);
    Evaluation deleteEvaluation(String id);
	Evaluation update(Evaluation evaluation);
	List<Evaluation> saveAll(List<Evaluation> list);
}
