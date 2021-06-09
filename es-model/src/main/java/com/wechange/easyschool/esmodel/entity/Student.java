package com.wechange.easyschool.esmodel.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
public class Student extends AbstractEntity{
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy=GenerationType.IDENTITY)
	 */
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private char sex;
	private Date bithDate;
	private String placeOfBith;
	private String parentNumber1;
	private String parentNumber2;
	private boolean redoublant;
	
	private Sequence seq1;
	private Sequence seq2;
	private Sequence seq3;
	private Sequence seq4;
	private Sequence seq5;
	private Sequence seq6; 
	
	private List<Evaluation> evaluation;
	
     @DBRef(lazy=false)  
	 @CascadeSave
	private Classe classe;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Student(String firstName, String lastName, char sex, Date bithDate, String placeOfBith, String parentNumber1,
			String parentNumber2) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.bithDate = bithDate;
		this.placeOfBith = placeOfBith;
		this.parentNumber1 = parentNumber1;
		this.parentNumber2 = parentNumber2;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public Date getBithDate() {
		return bithDate;
	}
	public void setBithDate(Date bithDate) {
		this.bithDate = bithDate;
	}
	public String getPlaceOfBith() {
		return placeOfBith;
	}
	public void setPlaceOfBith(String placeOfBith) {
		this.placeOfBith = placeOfBith;
	}
	public String getParentNumber1() {
		return parentNumber1;
	}
	public void setParentNumber1(String parentNumber1) {
		this.parentNumber1 = parentNumber1;
	}
	public String getParentNumber2() {
		return parentNumber2;
	}
	public void setParentNumber2(String parentNumber2) {
		this.parentNumber2 = parentNumber2;
	}
	
	
	public Classe getClasse() {
		return classe;
	}


	public void setClasse(Classe classe) {
		this.classe = classe;
	}


	public List<Evaluation> getEvaluation() {
		return evaluation;
	}


	public void setEvaluation(List<Evaluation> evaluation) {
		this.evaluation = evaluation;
	}


	public boolean isRedoublant() {
		return redoublant;
	}


	public void setRedoublant(boolean redoublant) {
		this.redoublant = redoublant;
	}


	public Sequence getSeq1() {
		return seq1;
	}


	public void setSeq1(Sequence seq1) {
		this.seq1 = seq1;
	}


	public Sequence getSeq2() {
		return seq2;
	}


	public void setSeq2(Sequence seq2) {
		this.seq2 = seq2;
	}


	public Sequence getSeq3() {
		return seq3;
	}


	public void setSeq3(Sequence seq3) {
		this.seq3 = seq3;
	}


	public Sequence getSeq4() {
		return seq4;
	}


	public void setSeq4(Sequence seq4) {
		this.seq4 = seq4;
	}


	public Sequence getSeq5() {
		return seq5;
	}


	public void setSeq5(Sequence seq5) {
		this.seq5 = seq5;
	}


	public Sequence getSeq6() {
		return seq6;
	}


	public void setSeq6(Sequence seq6) {
		this.seq6 = seq6;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
}
