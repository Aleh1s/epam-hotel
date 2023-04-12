<%@ attribute name="pagesNumber" type="java.lang.Integer" required="true" %>
<%@ attribute name="currPage" type="java.lang.Integer" required="true" %>
<%@ attribute name="command" type="java.lang.String" required="true" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="pagination">
    <c:choose>
        <c:when test="${pagesNumber gt 8}">
            <c:choose>
                <c:when test="${currPage - 3 <= 0}">
                    <c:forEach var="i" begin="1" end="7">
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
                    <div class="pagination-elem disabled">
                        ...
                    </div>
                    <div class="pagination-elem">
                        <a href="<c:url value="/controller?command=${command}&pageNumber=${pagesNumber}"/>">${pagesNumber}</a>
                    </div>
                </c:when>
                <c:when test="${currPage + 3 >= pagesNumber}">
                    <div class="pagination-elem">
                        <a href="<c:url value="/controller?command=${command}&pageNumber=1"/>">1</a>
                    </div>
                    <div class="pagination-elem disabled">
                        ...
                    </div>
                    <c:forEach var="i" begin="${pagesNumber - 7}" end="${pagesNumber}">
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
                </c:when>
                <c:otherwise>
                    <div class="pagination-elem">
                        <a href="<c:url value="/controller?command=${command}&pageNumber=1"/>">1</a>
                    </div>
                    <div class="pagination-elem disabled">
                        ...
                    </div>
                    <c:forEach var="i" begin="${currPage - 2}" end="${currPage + 2}">
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
                    <div class="pagination-elem disabled">
                        ...
                    </div>
                    <div class="pagination-elem">
                        <a href="<c:url value="/controller?command=${command}&pageNumber=${pagesNumber}"/>">${pagesNumber}</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
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
        </c:otherwise>
    </c:choose>
</div>