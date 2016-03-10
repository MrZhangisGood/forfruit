<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>
</head>
<body>
	User List Jsp.<br/>
	<span>name:${list[0].name}</span><br/>

	<hr/>

	<c:forEach items="${list}" var="user">
		<div>${user.name} : ${user.age}</div>
	</c:forEach>
</body>
</html>