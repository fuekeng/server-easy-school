package com.wechange.esclasseservice.service;

import java.util.List;
import java.util.Optional;

import com.wechange.easyschool.esmodel.entity.Classe;
public interface ClasseSearchService {
	public Optional<Classe> get(String id);
}
