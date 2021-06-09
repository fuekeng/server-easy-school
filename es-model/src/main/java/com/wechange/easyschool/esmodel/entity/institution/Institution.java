package com.wechange.easyschool.esmodel.entity.institution;

import com.wechange.easyschool.esmodel.entity.AbstractEntity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
@RequiredArgsConstructor
@Data
@Document(collection="Institution")

public class Institution extends AbstractEntity {
    
    @Id
 //   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 //   @Column(unique=true)
    private String code;
    private String name;
	private String region;
    private String departement;
    private String arrondissement;
 //   @Column(unique=true)
    private String email;
    private String telephone1;
    private String telephone2;
}
