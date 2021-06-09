// on met tous ce qui modifie la bd
package com.wechange.esclasseservice.service;

import com.wechange.easyschool.esmodel.entity.Classe;

public interface ClasseUpdateService {
    Classe createClasse(Classe classe);
    Classe deleteClasse(String id);
	Classe update(Classe classeDB);
}
