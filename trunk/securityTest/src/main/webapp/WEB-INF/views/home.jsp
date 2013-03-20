<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	<sec:authentication property="name"/> Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="/goodwilldd/j_spring_security_logout">로그아웃</a>
</body>
</html>
