<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>login page</title>
</head>
<body>
  <form action="http://localhost:8080/spring-mvc-shiro-a/login" method="POST">
    <input name="username" type="text" />
    <input name="password" type="password" />
    <input type="submit" value="登陆" />
  </form>
</body>
</html>