<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="type" type="java.lang.String" required="true" description="painging type [normal or top] if else is normal"%>
<%@ attribute name="divId" type="java.lang.String" required="true" description="paging div id"%>
<%@ attribute name="paging" type="com.phillit.pez.board.model.BoardPaging" required="true" description="Paging attribute"%>
<div class="centeringContainer">
    <span class="centered">
		<div id="${divId }">
			<c:choose>
			    <c:when test="${type eq 'top' }">
			        <ul>
						<li>${paging.totalCount } 개  중${paging.startPage } ~ ${paging.endPage }</li>
						<li id="pre">${paging.prePage }</li>
						<li id="next">${paging.nextPage }</li> 
			        </ul>
			    </c:when>
			    <c:when test="${type eq 'normal' }">
			       <ul>
		                <li>${paging.preBlock}</li>
		                <li>${paging.prePage }</li>
		                <c:forEach items="${paging.pages }" var="pages">
		                    <c:choose>
		                        <c:when test="${paging.currentPageNum eq pages}"><li id="current">${pages }</li></c:when>
		                        <c:otherwise><li>${pages }</li></c:otherwise>
		                    </c:choose>
		                </c:forEach>
		                <li>${paging.nextPage }</li>
		                <li>${paging.nextBlock }</li>
		            </ul>
			    </c:when>
			    <c:otherwise>
		            <ul>
		                <li>${paging.preBlock}</li>
		                <li>${paging.prePage }</li>
		                <c:forEach items="${paging.pages }" var="pages">
			            <li>${pages }</li>
			            </c:forEach>
		                <li>${paging.nextPage }</li>
		                <li>${paging.nextBlock }</li>
		            </ul>
			    </c:otherwise>
			</c:choose>
		</div>
	</span>
</div>
