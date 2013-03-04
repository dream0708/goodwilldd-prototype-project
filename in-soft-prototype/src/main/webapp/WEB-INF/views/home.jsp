<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<title>Home</title>
	<script type="text/javascript" src="${contextPath }/resources/js/jquery/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="${contextPath }/resources/js/common.js"></script>
</head>
<body>
<h1>
	Hello world! <spring:message code="myName"/>
</h1>

<P>  The time on the server is ${serverTime}. </P>
<button onclick="doAjax();">ajax</button><br />
<button onclick="doXml();">xml</button><br />
<div id="result"></div>
<script type="text/javascript">
function doAjax() {
	$.getJSON("${contextPath}/test", null, function(r) {
		console.log("json", r);
	});
}
function doXml() {
	$.getXML("${contextPath}/test", null, function(r) {
		console.log("xml", r);
	});
}
</script>
</body>
</html>
