<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Application list</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="component/header.jsp"/>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <c:choose>
                <c:when test="${not empty requestScope.applicationPage.result()}">
                    <div class="horizontal-delimiter-with-text">
                        <p><fmt:message key="reservations"/></p>
                        <div></div>
                    </div>
                    <table class="styled-table">
                        <thead>
                        <tr>
                            <th><fmt:message key="guests"/></th>
                            <th><fmt:message key="room.class"/></th>
                            <th><fmt:message key="check.in"/></th>
                            <th><fmt:message key="check.out"/></th>
                            <th><fmt:message key="control"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="application" items="${requestScope.applicationPage.result()}">
                            <tr>
                                <td>${application.guests()}</td>
                                <td>${application.roomClass()}</td>
                                <td>
                                    <fmt:formatDate type="date" value="${application.checkIn()}" pattern="dd-MM-yyyy"/>
                                </td>
                                <td>
                                    <fmt:formatDate type="date" value="${application.checkOut()}" pattern="dd-MM-yyyy"/>
                                </td>
                                <td>
                                    <form method="get" action="<c:url value="/controller"/>">
                                        <input type="hidden" name="command" value="viewApplicationDetails">
                                        <input type="hidden" name="applicationId" value="${application.id()}">
                                        <button type="submit" class="btn-view"><fmt:message key="details"/></button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <tags:notfount/>
                </c:otherwise>
            </c:choose>
            <tags:pagination pagesNumber="${requestScope.pagesNumber}" currPage="${requestScope.currPage}"
                             command="applicationList"/>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
