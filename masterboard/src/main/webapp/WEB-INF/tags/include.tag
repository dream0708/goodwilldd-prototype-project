<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ tag pageEncoding="UTF-8"%>
<title><s:message code="site.title"></s:message></title>
<c:url var="css" value="/resources/default.css" />
<c:url var="jquery" value="http://code.jquery.com/jquery-1.7.2.min.js" />
<link rel="stylesheet" href="${css }" />
<script type="text/javascript" src="${jquery }"></script>