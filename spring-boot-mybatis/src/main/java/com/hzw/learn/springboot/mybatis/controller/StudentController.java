package com.hzw.learn.springboot.mybatis.controller;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	@RequestMapping("addRandow")
	public Student addRandowStudent(){
		String studentId = String.valueOf(UUID.randomUUID().hashCode());
		
		String name = "RandowStudent" + new Random().nextInt();
		
		Student student = new Student();
		student.setStudentId(studentId);
		student.setName(name);
		Student result = studentDao.addStudent(student);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("delete/{id}")
	public String delStudent(@PathVariable("id") Integer id){
		String result = studentDao.delStudent(id);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("deleteid1")
	public String delStudentid1(){
		String result = studentDao.delStudentForId1();
		return result;
	}
	
	@ResponseBody
	@RequestMapping("update/{id}/{name}")
	public Student updateStudent(@PathVariable("id") Integer id, @PathVariable("name") String name){
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		Student result = studentDao.updateStudent(student);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("getAll")
	public List<Student> getAllStudent(){
		List<Student> studentList = studentDao.quetyStudentList(new Student());
		return studentList;
	}
}
