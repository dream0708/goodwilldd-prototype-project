<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<c:url var="jqueryUrl" value="/resources/jquery" />
<c:url var="common" value="/resources/js/common" />
<script type="text/javascript" src="${jqueryUrl}/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${common}/common.js"></script>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
<script type="text/javascript">
	setStepView(4);
	showAllStep();
</script>
</html>
