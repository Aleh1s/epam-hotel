<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
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
                        <td>
                            <a href="<c:url value="/controller?command=takeApplication&applicationId=${application.id}"/>"><fmt:message
                                    key="take"/></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <tags:pagination pagesNumber="${requestScope.pagesNumber}" currPage="${requestScope.currPage}"
                             command="applicationList"/>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
