package com.wechange.esclasseservice.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.wechange.easyschool.esmodel.entity.Classe;
import com.wechange.esclasseservice.repository.ClasseRepository;
import com.wechange.esclasseservice.service.ClasseSearchService;

@Service
public class ClasseSeachServiceImpl implements ClasseSearchService {

    private ClasseRepository classeRepository;

	public Optional<Classe> get(String id){
		return this.classeRepository.findById(id);
	}
	

}
