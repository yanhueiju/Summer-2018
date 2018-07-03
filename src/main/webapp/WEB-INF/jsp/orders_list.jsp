<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- 引入一个JSP的标签  JSTL-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	
	<table style="border: 2px solid #eee;">
		<tr>
			<th>下单用户ID</th>
			<th>下单用户名</th>
			<th>订单号</th>
			<th>创建订单时间</th>
			<th>订单备注</th>
		</tr>
		<c:forEach items="${orders}" var="order">
		<tr>
			<td>${order.user_id}</td>
			<td>${order.username}</td>
			<td>${order.number}</td>
			<c:if test="${order.createtime == null}">
				<td></td>
			</c:if>
			<c:if test="${order.createtime != null}">
				<td><fmt:formatDate value="${order.createtime }" pattern="yyyy-MM-dd"/> </td>
			</c:if>
			<td>${order.note}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>