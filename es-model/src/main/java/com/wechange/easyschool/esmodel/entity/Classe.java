package com.wechange.easyschool.esmodel.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.wechange.easyschool.esmodel.entity.user.User;

/**
 * @author Blondeau
 *
 */
@Document
public class Classe extends AbstractEntity {
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy= GenerationType.IDENTITY)
	 */
	@Id
	private String id;
	private String name;

	
	/*
	 * @DBRef(lazy = false)
	 * 
	 * @CascadeSave private Student chef;
	 */

	@DBRef(lazy = true)
	@CascadeSave 
	private User titulaire;
	 
	public Classe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * public Student getChef() { return chef; }
	 * 
	 * public void setChef(Student chef) { this.chef = chef; }
	 */
	
	  public User getTitulaire() { return titulaire; }
	  
	  public void setTitulaire(User titulaire) { this.titulaire = titulaire; }
	  

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

}
