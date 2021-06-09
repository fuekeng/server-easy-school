package com.wechange.esclasseservice.repository;

import com.wechange.easyschool.esmodel.entity.Classe;
import com.wechange.easyschool.esmodel.entity.user.User;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends MongoRepository<Classe,String>, PagingAndSortingRepository<Classe,String> {
	Optional<Classe> findById(ObjectId id);
	public List<Classe> findByDeleted(boolean b);
	public Page<Classe> findByNameContainingIgnoreCase(String title, Pageable paging);
	boolean existsById(String id);
	public Page<Classe> findByDeleted(Boolean deleted,Pageable paging);
	
}
