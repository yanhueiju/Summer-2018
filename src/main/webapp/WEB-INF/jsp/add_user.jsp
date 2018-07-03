<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
//通过JSP的 代码，在浏览器端来获取 java后台访问的根URL
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
+ request.getServerPort()+ path + "/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!--  添加一个设置当前界面中，所有请求的  根URl路径  -->
<!-- <base href="http://localhost:8089/maven_springmvc_project/"> -->
<base href="<%= basePath%>">
</head>
<body>

	<form action="user/add.action" method="post">
		请填写用户名：<input type="text" name="username" /> 请填写用户地址：<input
			type="text" name="address" />
		<button type="submit">提交信息</button>
	</form>
</body>
</html>