package com.hzw.learn.springboot.mybatis.model;

import java.io.Serializable;

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String studentId;

	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode())
				+ "[" + 
				"serialVersionUID=" + serialVersionUID + 
				",id=" + id + 
				",studentId=" + studentId + 
				",name=" + name + 
				"]";
	}
}
