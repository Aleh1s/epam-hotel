<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<tags:errorhandler message="${sessionScope.errorMessage}"/>

<div class="header">
    <div class="nav">
        <div class="nav-left">
            <div class="i18n">
                <form action="<c:url value="/controller"/>" method="post" class="i18n-ua">
                    <input type="hidden" name="command" value="i18n">
                    <input type="hidden" name="lang" value="ua">
                    <button class="i18n-ua-button" type="submit"></button>
                </form>
                <span class="vertical-delimiter"></span>
                <form action="<c:url value="/controller"/>" method="post" class="i18n-en">
                    <input type="hidden" name="command" value="i18n">
                    <input type="hidden" name="lang" value="en">
                    <button class="i18n-en-button" type="submit"></button>
                </form>
            </div>
        </div>
        <ul class="nav-center">
            <li class="nav-item"><a href="<c:url value="/controller?command=roomList&default=on"/>"><fmt:message
                    key="room.list"/></a></li>
            <c:if test="${not empty sessionScope.role and sessionScope.role eq 'CUSTOMER'}">
                <li class="nav-item"><a href="<c:url value="/jsp/application.jsp"/>"><fmt:message
                        key="application"/></a></li>
            </c:if>
            <c:if test="${not empty sessionScope.role and sessionScope.role eq 'MANAGER'}">
                <li class="nav-item"><a href="<c:url value="/controller?command=reservationList&default=on"/>"><fmt:message
                        key="reservations"/></a></li>
                <li class="nav-item"><a href="<c:url value="/controller?command=applicationList"/>"><fmt:message
                        key="applications"/></a></li>
            </c:if>
        </ul>
        <ul class="nav-right">
            <li class="nav-item"><a href="<c:url value="/jsp/login.jsp"/>"><fmt:message key="log.in"/></a></li>
            <li class="nav-item"><a href="<c:url value="/jsp/signup.jsp"/>"><fmt:message key="sign.up"/></a></li>
            <c:if test="${not empty sessionScope.id}">
                <li class="nav-item"><a href="<c:url value="/controller?command=profile"/>"><fmt:message
                        key="profile"/></a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
</body>
</html>
