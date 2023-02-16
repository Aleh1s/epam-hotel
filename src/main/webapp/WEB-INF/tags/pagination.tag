<%@ attribute name="pagesNumber" type="java.lang.Integer" required="true" %>
<%@ attribute name="currPage" type="java.lang.Integer" required="true" %>
<%@ attribute name="command" type="java.lang.String" required="true" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="pagination">
    <c:forEach var="i" begin="1" end="${pagesNumber}">
        <c:choose>
            <c:when test="${currPage eq i}">
                <div class="pagination-elem active">
                    <a href="#">${i}</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="pagination-elem">
                    <a href="<c:url value="/controller?command=${command}&pageNumber=${i}"/>">${i}</a>
                </div>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>