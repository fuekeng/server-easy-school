package com.wechange.easyschool.esmodel.entity.course;

import org.springframework.data.annotation.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.wechange.easyschool.esmodel.entity.AbstractEntity;
import com.wechange.easyschool.esmodel.entity.CascadeSave;
import com.wechange.easyschool.esmodel.entity.Classe;
import com.wechange.easyschool.esmodel.entity.Student;

import lombok.Data;
import lombok.RequiredArgsConstructor;




//@Data on n'a pas besoin d'inclure les getters et setter
@Document(collection="course")
public class Course extends AbstractEntity{
	
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	@Id
	private String id;
	private String code;
	
	@DBRef(lazy=true)  //quand j'ajouter le champ db="classe sa ne charge par la classe"
	@CascadeSave
	private Classe classe;
	 	
	private String enseignant;
	private String departement;
	@Length(min=2, max=20,message="l'intitulé doit avoir au plus 20 caractères")
	String intitule;
	@Min(value = 0, message="le coeficient doit être supérieur à 0")
	@Max(value =10 , message="le coeficient doit être inférieur à 10")
	private int coef;
	private int nombre_heures_prevues; 
	private int nombre_heures_faire;
	private int nombre_lecons_prevues;
	private int nombre_lecons_faites;

	
	

	
	public Classe getClasse() {
		return classe;
	}



	public void setClasse(Classe classe) {
		this.classe = classe;
	}



	public String getEnseignant() {
		return enseignant;
	}



	public void setEnseignant(String enseignant) {
		this.enseignant = enseignant;
	}



	public String getDepartement() {
		return departement;
	}



	public void setDepartement(String departement) {
		this.departement = departement;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public int getCoef() {
		return coef;
	}
	public void setCoef(int coef) {
		this.coef = coef;
	}
	public int getNombre_heures_prevues() {
		return nombre_heures_prevues;
	}
	public void setNombre_heures_prevues(int nombre_heures_prevues) {
		this.nombre_heures_prevues = nombre_heures_prevues;
	}
	public int getNombre_heures_faire() {
		return nombre_heures_faire;
	}
	public void setNombre_heures_faire(int nombre_heures_faire) {
		this.nombre_heures_faire = nombre_heures_faire;
	}
	public int getNombre_lecons_prevues() {
		return nombre_lecons_prevues;
	}
	public void setNombre_lecons_prevues(int nombre_lecons_prevues) {
		this.nombre_lecons_prevues = nombre_lecons_prevues;
	}
	public int getNombre_lecons_faites() {
		return nombre_lecons_faites;
	}
	public void setNombre_lecons_faites(int nombre_lecons_faites) {
		this.nombre_lecons_faites = nombre_lecons_faites;
	}
	
	

}
