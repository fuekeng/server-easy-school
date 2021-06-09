package com.wechange.esclasseservice.service.impl;

import com.wechange.easyschool.esmodel.entity.Classe;
import com.wechange.esclasseservice.repository.ClasseRepository;
import com.wechange.esclasseservice.service.ClasseRetrieveService;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseRetrieveServiceImpl implements ClasseRetrieveService {

    private ClasseRepository classeRepository;

    public ClasseRetrieveServiceImpl(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

	public List<Classe> getAll(){
		return this.classeRepository.findByDeleted(false);	
	}
	
	public Optional<Classe> get(String id){
		return this.classeRepository.findById(id);			 
	}
	
	public Page<Classe> findByIntituleContainingIgnoreCase(String title, Pageable paging){
		return this.classeRepository.findByNameContainingIgnoreCase(title, paging);
		 
	 }
	
	public Page<Classe>  getAll(Pageable paging){
		return this.classeRepository.findAll(paging);
	}
	


	@Override
	public Page<Classe> findByPageDeleted(Boolean deleted,Pageable paging) {
		// TODO Auto-generated method stub
		return this.classeRepository.findByDeleted(deleted,paging);
	}


}
