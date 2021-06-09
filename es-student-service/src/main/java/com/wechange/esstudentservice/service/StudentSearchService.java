package com.wechange.esstudentservice.service;

import java.util.List;
import java.util.Optional;

import com.wechange.easyschool.esmodel.entity.Student;
public interface StudentSearchService {
	public Optional<Student> get(String id);
}
