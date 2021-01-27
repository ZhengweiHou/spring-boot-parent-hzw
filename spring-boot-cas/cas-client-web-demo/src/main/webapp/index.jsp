<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.jasig.cas.client.validation.Assertion"%>
<%@page import="org.jasig.cas.client.util.AbstractCasFilter"%>
<%@page import="org.jasig.cas.client.authentication.AttributePrincipal"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <h1><%=path%></h1>
  	你已经登录进来了兄弟，下面是你的登录信息：
  	<%
  	AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal(); 
  	Map<String, Object> attributes = principal.getAttributes();
  	String userName = principal.getName();
  	//String userClass = "";
  	String userName2 = ((Assertion)(request.getSession().getAttribute("_const_cas_assertion_"))).getPrincipal().getName(); 
  	if(attributes != null) {
  		//userId = attributes.get("uid").toString();
  		//userClass = attributes.get("UserClass").toString();
  		System.out.println("attributes:" + attributes);
  		System.out.println("session:" + session);
  		
  		Enumeration enums = session.getAttributeNames();
  		System.out.println("session Attribus:" + enums);
  		while(enums.hasMoreElements()){
  			System.out.println("enums.nextElement:" + enums.nextElement());
  		}
  	}
  	%>
  	<p>用户名：<%=userName %></p>
  	<%=userName2 %>
  	<%
  		if(session != null){%>
  			<p><%=session.getId() %></p>		
  		<%}
  	%>
  	<p>
  	<a href=hzw.html>hzw</a>
  	<p>
  	<a href="logout.jsp">退出登录</a>
  </body>
</html>
