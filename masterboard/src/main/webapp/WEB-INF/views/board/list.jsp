<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pez" tagdir="/WEB-INF/tags" %>
<%@page import="com.phillit.pez.board.model.BoardSearchParam"%>
<%@page import="java.util.EnumSet"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<form:form commandName="boardListModel">
<c:set var="currentPageNum" value="1" />
<table width="100%">
    <thead>
        <tr>
            <th width="60%">subject</th>
            <th width="20%">register</th>
            <th width="20%">regdate</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${boardListModel.list }" var="list">
        <tr>
            <td>${list.subject }</td>
            <td>${list.register }</td>
            <td>${list.regdate }</td>
        </tr>
        </c:forEach>
    </tbody>
</table>
<c:choose>
    <c:when test="${boardListModel.currentPageNum == 0}"><c:set var="currentPageNum" value="1" /></c:when>
    <c:otherwise><c:set var="currentPageNum" value="${boardListModel.currentPageNum }" /></c:otherwise>
</c:choose>
<form:hidden path="currentPageNum" value="${currentPageNum }" />
<form:hidden path="boardName" value="${boardListModel.boardName }" />
<table width="100%">
    <thead>
    </thead>
    <tbody>
        <tr>
            <td>
            <%-- <form:select path="searchField">
                <form:options items="${boardListModel.searchField}" itemValue="item" itemLabel="localizationKey" />
            </form:select> --%>
            <pez:selectbox path="searchField" items="<%=EnumSet.allOf(BoardSearchParam.class) %>" itemLabel="localizationKey" itemValue="item" localize="true" />
            <form:input path="searchValue" value="${boardListModel.searchValue }"/>
            <form:button>검색</form:button>
            </td>
        </tr>
    </tbody>
</table>
</form:form>
</body>
</html>
