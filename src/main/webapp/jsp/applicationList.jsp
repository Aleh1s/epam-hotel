<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Application list</title>
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<c:import url="component/header.jsp"/>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<div class="container">
    <div style="height: 100%" class="main">
        <div class="main-container">
            <c:choose>
                <c:when test="${not empty requestScope.applicationPage.result()}">
                    <fmt:message var="reservationsMessage" key="reservations"/>
                    <tags:horizontaldelimiter message="${reservationsMessage}"/>
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
                                <td>${application.guests}</td>
                                <td>${application.roomClass}</td>
                                <td>${application.checkIn}</td>
                                <td>${application.checkOut}</td>
                                <td>
                                    <form method="get" action="<c:url value="/controller"/>">
                                        <input type="hidden" name="command" value="viewApplicationDetails">
                                        <input type="hidden" name="applicationId" value="${application.id}">
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
</div>
<c:import url="component/footer.jsp"/>
</body>
</html>
