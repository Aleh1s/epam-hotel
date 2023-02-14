<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${requestScope.lang}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Application list</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <table class="styled-table">
                <thead>
                <tr>
                    <th><fmt:message key="number.of.guests"/></th>
                    <th><fmt:message key="room.class"/></th>
                    <th><fmt:message key="entry.date"/></th>
                    <th><fmt:message key="leaving.date"/></th>
                    <th><fmt:message key="status"/></th>
                    <th><fmt:message key="customer.id"/></th>
                    <th><fmt:message key="control"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="application" items="${requestScope.applicationList}">
                    <tr>
                        <td>${application.guestsNumber}</td>
                        <td>${application.roomClass}</td>
                        <td>${application.entryDate}</td>
                        <td>${application.leavingDate}</td>
                        <td>${application.status}</td>
                        <td>${application.customerId}</td>
                        <td><a href="<c:url value="/controller?command=takeApplication&applicationId=${application.id}"/>"><fmt:message key="take"/></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="pagination">
                <c:forEach var="i" begin="1" end="${requestScope.pagesNumber}">
                    <c:choose>
                        <c:when test="${requestScope.currPage eq i}">
                            <div class="pagination-elem active">
                                <a href="#">${i}</a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="pagination-elem">
                                <a href="<c:url value="/controller?command=applicationList&pageNumber=${i}"/>">${i}</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
