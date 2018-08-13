package com.hzw.learn.springboot.mybatis.controller;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzw.learn.springboot.mybatis.dao.StudentDao;
import com.hzw.learn.springboot.mybatis.model.Student;

@Controller
@RequestMapping("student")
public class StudentController {
	
	@Autowired
	private StudentDao studentDao;
	
	@ResponseBody
	@RequestMapping("addRandowStudent")
	public String addRandowStudent(){
		String studentId = String.valueOf(UUID.randomUUID().hashCode());
		
		String name = "RandowStudent" + new Random().nextInt();
		
		Student student = new Student();
		student.setStudentId(studentId);
		student.setName(name);
		Integer result = studentDao.addStudent(student);
		
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("getAllStudent")
	public List<Student> getAllStudent(){
		List<Student> studentList = studentDao.quetyStudentList(new Student());
		return studentList;
	}
}
