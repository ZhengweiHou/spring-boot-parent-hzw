package com.hzw.learn.springboot.mybatis.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hzw.learn.springboot.mybatis.dao.StudentDao;
import com.hzw.learn.springboot.mybatis.model.Student;
import com.hzw.learn.springboot.mybatis.plugins.QueryInterceptor;

@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public Student addStudent(Student student) {
		logger.info("addStudent sutdent:【{}】", student.toString());
		String insertSqlId = "com.hzw.learn.springboot.mybatis.dao.student.insert";
		Integer result = sqlSession.insert(insertSqlId, student);
		return student;
	}

	@Override
	public String delStudent(Integer id) {
		logger.info("delStudent id:【{}】", id);
		String delSqlId = "com.hzw.learn.springboot.mybatis.dao.student.deleteByPrimaryKey";
		Student student = new Student();
		student.setId(id);
		int result = sqlSession.delete(delSqlId, student);
		return id.toString();
	}

	@Override
	public String delStudentForId1() {
		logger.info("delStudent id=1");
		String delSqlId = "com.hzw.learn.springboot.mybatis.dao.student.deleteForId1";
		Integer result = sqlSession.delete(delSqlId);
		return result.toString();
	}

	@Override
	public String updateStudent(Student student) {
		logger.info("updateStudent student:【{}】", student);
		String delSqlId = "com.hzw.learn.springboot.mybatis.dao.student.updateNotNullByPrimaryKey";
		Integer result = sqlSession.update(delSqlId, student);
		return result.toString();
	}

	@Override
	public List<Student> quetyStudentList(Student student) {
		logger.info("quetyStudentList sutdent:【{}】", student.toString());
		String queryAllSqlId = "com.hzw.learn.springboot.mybatis.dao.student.selectAll";
		List<Student> result = sqlSession.selectList(queryAllSqlId, student);
		return result;
	}
	
	@Override
	public List<Map<String, String>> quetyStudentListMap(Student student) {
		logger.info("quetyStudentList sutdent:【{}】", student.toString());
		String queryAllSqlId = "com.hzw.learn.springboot.mybatis.dao.student.selectAll2";
		List<Map<String, String>> result = sqlSession.selectList(queryAllSqlId, student);
		return result;
	}

}
