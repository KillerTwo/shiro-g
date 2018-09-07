<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
  <h1>welcome ... ${ sessionScope.username } </h1>
  <h2> ${ username } </h2>
  <a href="http://localhost:8080/spring-mvc-shiro-a/logout">退出</a>
</body>
</html>