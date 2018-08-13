package com.hzw.learn.springboot.mybatis.dao;

import java.util.List;

import com.hzw.learn.springboot.mybatis.model.Student;

public interface StudentDao {
	
	Integer addStudent(Student student);
	
	List<Student> quetyStudentList(Student student);
}
