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
<div class="container">
    <div class="main">
        <div class="main-container">
            <c:choose>
                <c:when test="${not empty requestScope.applicationPage.result}">
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
                        <c:forEach var="application" items="${requestScope.applicationPage.result}">
                            <tr>
                                <td>${application.guests}</td>
                                <td>${application.roomClass}</td>
                                <td>${application.checkIn}</td>
                                <td>${application.checkOut}</td>
                                <td>
                                    <form method="get" action="<c:url value="/controller"/>">
                                        <input type="hidden" name="command" value="viewApplicationDetails">
                                        <input type="hidden" name="applicationId" value="${application.id}">
                                        <button type="submit" class="btn-view">Details</button>
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
