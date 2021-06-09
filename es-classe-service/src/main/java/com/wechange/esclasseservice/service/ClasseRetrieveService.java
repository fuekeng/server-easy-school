package com.wechange.esclasseservice.service;

import com.wechange.easyschool.esmodel.entity.Classe;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// tous ce qui recupère quelque chose de la bd
public interface ClasseRetrieveService {
	 public List<Classe> getAll();
	 public Optional<Classe> get(String id);
	 public Page<Classe> findByIntituleContainingIgnoreCase(String title, Pageable paging);
	 public Page<Classe>  getAll(Pageable paging);
	 public Page<Classe> findByPageDeleted(Boolean deleted,Pageable paging);
    
}
