<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springs" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pez" tagdir="/WEB-INF/tags" %>
<c:url var="submitUrl" value="save" />
<c:url var="deleteSubmit" value="../delete/save" />
<c:url var="list" value="../list" />
<c:url var="replyform" value="../reply/form?bSeq=${param.bSeq }" />
<c:url var="modifyform" value="../modify/form?bSeq=${param.bSeq }" />
<html>
<head>
	<pez:include />
</head>
<body>
<h1>
	${action }  ${requestScope["javax.servlet.forward.request_uri"]}
</h1>
<form:form commandName="boardDataModel" htmlEscape="true" action="${submitUrl }" method="post">
<form:hidden path="boardName" />
<form:hidden path="register" />
<form:hidden path="bSeq" />
<c:set var="css" value="editable"/>
<c:set var="readonly" value="false"/>
<c:choose>
    <c:when test="${action eq 'read'}">
        <c:set var="css" value="non-editable"/>
        <c:set var="readonly" value="true"/>
    </c:when>
    <c:otherwise>
        <c:set var="css" value="editable"/>
        <c:set var="readonly" value="false"/>
    </c:otherwise>
</c:choose>
<div id="form-content" class="${css }">
<p>
    <label for="subject">제목</label>
    <form:input path="subject" value="${boardDataModel.subject }" readonly="${readonly }"/>
    <form:errors path="subject">
    <c:forEach items="${messages}" var="message">
        <div class="errors"><springs:message code="${message}" /></div>
    </c:forEach>
    </form:errors>
</p>
<p>
    <label for="subject">내용</label>
    <form:textarea path="content" value="${boardDataModel.content }"  readonly="${readonly }"/>
    <form:errors path="content">
    <c:forEach items="${messages}" var="message">
        <div class="errors"><springs:message code="${message}" /></div>
    </c:forEach>
    </form:errors>
</p>
<c:choose>
    <c:when test="${action eq 'read'}">
		<p>
		    <button type="button" onclick="location.href='${list}';">목록</button>
		    <button type="button" onclick="location.href='${replyform}';">답글</button>
		    <button type="button" onclick="location.href='${modifyform}';">수정</button>
		    <button type="button" onclick="doDelete();">삭제</button>
		</p>
    </c:when>
    <c:otherwise>
        <p>
		    <button>작성</button>
		</p>
    </c:otherwise>
</c:choose>
</div>
</form:form>
<script type="text/javascript">
function doDelete() {
	$("#boardDataModel").attr("action", "${deleteSubmit}");
	$("#boardDataModel").submit();
}
</script>
</body>
</html>
