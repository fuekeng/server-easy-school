package com.wechange.esevaluationservice.repository;

import com.wechange.easyschool.esmodel.entity.Evaluation;
//import com.wechange.easyschool.esmodel.entity.user.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends MongoRepository<Evaluation,String>, PagingAndSortingRepository<Evaluation,String> {
	Optional<Evaluation> findById(String id);
	public List<Evaluation> findByDeleted(boolean b);
	public Page<Evaluation> findByStudentContainingIgnoreCase(String title, Pageable paging);
	boolean existsById(String id);
	public Page<Evaluation> findByDeleted(Boolean deleted,Pageable paging);
	
}
