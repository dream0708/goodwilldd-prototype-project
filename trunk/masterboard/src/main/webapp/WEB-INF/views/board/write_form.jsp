<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springs" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url var="submitUrl" value="/b/write" />
<c:url var="css" value="/resources/default.css" />
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="${css }" />
</head>
<body>
<h1>
	write  ${requestScope["javax.servlet.forward.request_uri"]}
</h1>
<form:form commandName="boardDataModel" htmlEscape="true" action="${submitUrl }" method="post">
<form:hidden path="boardName" value="${boardDataModel.boardName }" />
<form:hidden path="register" value="${boardDataModel.register }" />
<p>
	<label for="subject">제목</label>
	<form:input path="subject" value="${boardDataModel.subject }"/>
	<form:errors path="subject">
	<c:forEach items="${messages}" var="message">
		<div class="errors"><springs:message code="${message}" /></div>
	</c:forEach>
	</form:errors>
</p>
<p>
	<label for="subject">내용</label>
	<form:textarea path="content" value="${boardDataModel.content }" />
	<form:errors path="content">
	<c:forEach items="${messages}" var="message">
		<div class="errors"><springs:message code="${message}" /></div>
	</c:forEach>
	</form:errors>
</p>
<p>
	<button type="submit">작성</button>
</p>
</form:form>
</body>
</html>
