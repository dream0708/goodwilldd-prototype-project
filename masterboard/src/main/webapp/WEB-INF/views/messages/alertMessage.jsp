<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springs" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url var="css" value="/resources/default.css" />
<html>
<head>
<link rel="stylesheet" href="${css }" />
</head>
<body>
<div class="box">
<div class="errors"><springs:message code="${messageCode }" /></div>
<div class="errors_detail">${exceptionMsg }</div>
</div>
</body>
</html>
