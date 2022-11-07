package com.hzw.learn.springboot.mybatis.controller;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzw.learn.springboot.mybatis.dao.StudentDao;
import com.hzw.learn.springboot.mybatis.model.Student;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("student")
public class StudentController {

	@Autowired
	private StudentDao studentDao;

	@ResponseBody
	@RequestMapping("addRandow")
	public Student addRandowStudent(HttpServletRequest req) {

		String name = req.getParameter("name");

		String studentId = String.valueOf(UUID.randomUUID().hashCode());

		if (StringUtils.isEmpty(name))
			name = "RandowStudent" + new Random().nextInt();

		Student student = new Student();
		student.setStudentId(studentId);
		student.setName(name);
		Student result = studentDao.addStudent(student);

		return result;
	}


	@ResponseBody
	@RequestMapping("delete/{id}")
	public String delStudent(@PathVariable("id") Integer id) {
		String result = studentDao.delStudent(id);

		return result;
	}

	@ResponseBody
	@RequestMapping("deleteid1")
	public String delStudentid1() {
		String result = studentDao.delStudentForId1();
		return result;
	}

	@ResponseBody
	@RequestMapping("update/{id}/{name}")
	public String updateStudent(@PathVariable("id") Integer id, @PathVariable("name") String name) {
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		String result = studentDao.updateStudent(student);
		return result;
	}

	@ResponseBody
	@RequestMapping("getall")
	public List<Student> getAllStudent() {
		List<Student> studentList = studentDao.quetyStudentList(new Student());
		return studentList;
	}
	
	@ResponseBody
	@RequestMapping("getall2")
	public String getAllStudent2() {
		List<Map<String, String>> studentList = studentDao.quetyStudentListMap(new Student());
		return studentList.toString();
	}
}
