package com.hzw.learn.springboot.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hzw.learn.springboot.mybatis.dao.StudentDao;
import com.hzw.learn.springboot.mybatis.model.Student;

@Component
public class RunStart implements CommandLineRunner {
	@Autowired
	private StudentDao studentDao;

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("run start ...");
//		studentDao.quetyStudentList(new Student());

	}

}
