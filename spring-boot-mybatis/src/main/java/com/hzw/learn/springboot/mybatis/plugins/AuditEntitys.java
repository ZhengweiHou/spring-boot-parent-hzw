package com.hzw.learn.springboot.mybatis.plugins;

import java.util.Map;

public class AuditEntitys {
	private Map<String, Map<String, String>> auditEntitys;

	public Map<String, Map<String, String>> getAuditEntitys() {
		return auditEntitys;
	}

	public void setAuditEntitys(Map<String, Map<String, String>> auditEntitys) {
		this.auditEntitys = auditEntitys;
	}
}
