// on met tous ce qui modifie la bd
package com.wechange.esstudentservice.service;

import com.wechange.easyschool.esmodel.entity.Student;

public interface StudentUpdateService {
    Student createStudent(Student student);
    Student deleteStudent(String id);
	Student update(Student coursDB);
}
