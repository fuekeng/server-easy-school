package com.wechange.easyschool.esmodel.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.wechange.easyschool.esmodel.entity.course.Course;

import lombok.Data;

@Document
public class Evaluation extends AbstractEntity{
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy=GenerationType.IDENTITY)
	 */
	private String id;
	
	@DBRef(lazy = true)
	@CascadeSave
	private Course course;
	
	@DBRef(lazy = false)
	@CascadeSave
	private Student student;
	
	private String seq1;
	private String seq2;
	private String seq3;
	private String seq4;
	private String seq5;
	private String seq6;
	
	
	public Evaluation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getSeq1() {
		return seq1;
	}
	public void setSeq1(String seq1) {
		this.seq1 = seq1;
	}
	public String getSeq2() {
		return seq2;
	}
	public void setSeq2(String seq2) {
		this.seq2 = seq2;
	}
	public String getSeq3() {
		return seq3;
	}
	public void setSeq3(String seq3) {
		this.seq3 = seq3;
	}
	public String getSeq4() {
		return seq4;
	}
	public void setSeq4(String seq4) {
		this.seq4 = seq4;
	}
	public String getSeq5() {
		return seq5;
	}
	public void setSeq5(String seq5) {
		this.seq5 = seq5;
	}
	public String getSeq6() {
		return seq6;
	}
	public void setSeq6(String seq6) {
		this.seq6 = seq6;
	}
	@Override
	public String toString() {
		return "Evaluation [id=" + id + ", course=" + course + ", Student=" + student + ", seq1=" + seq1 + ", seq2="
				+ seq2 + ", seq3=" + seq3 + ", seq4=" + seq4 + ", seq5=" + seq5 + ", seq6=" + seq6 + "]";
	}
	
	
	
	
	
}
