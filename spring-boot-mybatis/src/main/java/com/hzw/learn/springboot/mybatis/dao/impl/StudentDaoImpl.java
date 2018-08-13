package com.hzw.learn.springboot.mybatis.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hzw.learn.springboot.mybatis.dao.StudentDao;
import com.hzw.learn.springboot.mybatis.model.Student;
import com.hzw.learn.springboot.mybatis.plugins.QueryInterceptor;

@Repository("studentDao")
public class StudentDaoImpl implements StudentDao{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Integer addStudent(Student student) {
		logger.info("addStudent sutdent:[{}]",student.toString());
		String insertSqlId = "com.hzw.learn.springboot.mybatis.dao.student.insert";
		Integer result = sqlSession.insert(insertSqlId, student);
		return result;
	}

	@Override
	public List<Student> quetyStudentList(Student student) {
		logger.info("quetyStudentList sutdent:[{}]",student.toString());
		String queryAllSqlId = "com.hzw.learn.springboot.mybatis.dao.student.selectAll";
		List<Student> result = sqlSession.selectList(queryAllSqlId, student);
		return result;
	}

}
