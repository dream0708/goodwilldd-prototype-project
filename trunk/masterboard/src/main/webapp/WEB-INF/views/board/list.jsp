<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pez" tagdir="/WEB-INF/tags" %>
<%@page import="com.phillit.pez.board.model.BoardSearchParam"%>
<%@page import="java.util.EnumSet"%>
<c:url var="writeform" value="write/form" />
<c:url var="readform" value="read/form" />
<html>
<head>
	<pez:include />
</head>
<body>
<form:form commandName="boardListModel" method="post">
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
            <td>
                <span style="margin-left: ${list.reLevel*20 }px;"><a href="${readform }?bSeq=${list.bSeq}">${list.subject }</a></span>
            </td>
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
<form:hidden path="currentPageNum" />
<form:hidden path="boardName" />
<table width="100%">
    <thead></thead>
    <tbody>
        <tr>
            <td align="center">
            <%-- <form:select path="searchField">
                <form:options items="${boardListModel.searchField}" itemValue="item" itemLabel="localizationKey" />
            </form:select> --%>
            <pez:selectbox path="searchField" items="<%=EnumSet.allOf(BoardSearchParam.class) %>" itemLabel="localizationKey" itemValue="item" localize="true" />
            <form:input path="searchValue" value="${boardListModel.searchValue }"/>
            <form:button>검색</form:button>
            </td>
        </tr>
        <tr>
            <td align="center"><pez:paging paging="${boardListModel.paging }" type="normal" divId="paging" /></td>
        </tr>
        <tr>
            <td align="center"><pez:paging paging="${boardListModel.paging }" type="top"  divId="paging" /></td>
        </tr>
        <tr>
            <td align="right">
                <form:button type="button" onclick="location.href='${writeform }';">쓰기</form:button>
            </td>
        </tr>
    </tbody>
</table>
</form:form>
</body>
</html>
