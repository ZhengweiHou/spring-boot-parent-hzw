package com.hzw.learn.springboot.mybatis.dao;

import java.util.List;

import com.hzw.learn.springboot.mybatis.model.Student;
import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

public interface StudentDao {

	Student addStudent(Student student);

	String delStudent(Integer id);

	String delStudentForId1();

	String updateStudent(Student student);

	List<Student> quetyStudentList(Student student);
	
	List<Map<String, String>> quetyStudentListMap(Student student);
}
