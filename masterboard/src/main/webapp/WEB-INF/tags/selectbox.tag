<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="path" type="java.lang.String" required="true"
	description="Path to property for data binding"%>
<%@ attribute name="value" type="java.lang.String" required="false"
	description="this value equals is selected"%>
<%@ attribute name="items" type="java.lang.Object"
	description="The Collection, Map or array of objects used to generate the inner 'option' tags"%>
<%@ attribute name="itemValue" type="java.lang.String"
	description="Name of the property mapped to 'value' attribute of the 'option' tag."%>
<%@ attribute name="itemLabel" type="java.lang.String"
	description="Name of the property mapped to the inner text of the 'option' tag."%>
<%@ attribute name="localize" type="java.lang.Boolean"
	description="True if localize option inner text."%>
<form:select path="${path}">
	<c:forEach var="i" items="${items}">
		<c:choose>
			<c:when test="${!empty value }">
				<c:choose>
					<c:when test="${i[itemValue] eq value }">
						<form:option value="${i[itemValue]}" selected="selected">
							<c:choose>
								<c:when test="${localize}">
									<spring:message code="${i[itemLabel]}" text="${i[itemLabel]}" />
								</c:when>
								<c:otherwise>
									<c:out value="${i[itemLabel]}" />
								</c:otherwise>
							</c:choose>
						</form:option>
					</c:when>
					<c:otherwise>
						<form:option value="${i[itemValue]}">
							<c:choose>
								<c:when test="${localize}">
									<spring:message code="${i[itemLabel]}" text="${i[itemLabel]}" />
								</c:when>
								<c:otherwise>
									<c:out value="${i[itemLabel]}" />
								</c:otherwise>
							</c:choose>
						</form:option>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<form:option value="${i[itemValue]}">
					<c:choose>
						<c:when test="${localize}">
							<spring:message code="${i[itemLabel]}" text="${i[itemLabel]}" />
						</c:when>
						<c:otherwise>
							<c:out value="${i[itemLabel]}" />
						</c:otherwise>
					</c:choose>
				</form:option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</form:select>