package com.wechange.esclasseservice.service.impl;

import com.wechange.easyschool.esmodel.entity.Classe;
import com.wechange.easyschool.esmodel.entity.institution.Institution;
import com.wechange.esclasseservice.repository.ClasseRepository;
import com.wechange.esclasseservice.service.ClasseUpdateService;

import ch.qos.logback.classic.Logger;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class ClasseUpdateServiceImpl implements ClasseUpdateService {

    private ClasseRepository classeRepository;

    public ClasseUpdateServiceImpl(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    @Override
    public Classe createClasse(Classe classe) {
        classe.setCreatedAt(new Date());
        return classeRepository.save(classe);
    }
    
    public Classe update(Classe classe) {
        return classeRepository.save(classe);
    }
    
    public Classe deleteClasse(String id) {
        Optional <Classe> optional=classeRepository.findById(id);
        if(optional.isPresent()){
            Classe classe=optional.get();
            classe.setDeleted(Boolean.TRUE);
            classe.setDeletedAt(new Date());
            return classeRepository.save(classe);
        }

        //institution.setIdCreator();
           return null;
    }
}
