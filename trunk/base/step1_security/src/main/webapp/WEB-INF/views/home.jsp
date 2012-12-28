<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<script type="text/javascript" src='<c:url value="/resources/js/jquery-1.8.3.min.js"/>'></script>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href='<c:url value="/j_spring_security_logout"/>'>로그아웃</a>
</body>
</html>
