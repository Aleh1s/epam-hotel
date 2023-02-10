<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<c:url value="/controller" var="roomList">
    <c:param name="command" value="roomList"/>
    <c:param name="pageNumber" value="1"/>
    <c:param name="priceFrom" value="20"/>
    <c:param name="priceTo" value="500"/>
    <c:param name="personsFrom" value="1"/>
    <c:param name="personsTo" value="10"/>
    <c:param name="standard" value="on"/>
    <c:param name="superior" value="on"/>
    <c:param name="family" value="on"/>
    <c:param name="business" value="on"/>
    <c:param name="president" value="on"/>
    <c:param name="free" value="on"/>
    <c:param name="booked" value="on"/>
    <c:param name="busy" value="on"/>
    <c:param name="unavailable" value="on"/>
</c:url>

<div class="header">
    <div class="nav">
        <ul class="nav-left">
            <li class="nav-item"><a href="controller?command=i18n&lang=en">EN</a></li>
            <span class="vertical-delimiter"></span>
            <li class="nav-item"><a href="controller?command=i18n&lang=ua">UA</a></li>
        </ul>
        <ul class="nav-center">
            <li class="nav-item"><a href="#"><b><fmt:message key="nav.label.home"/></b></a></li>
            <li class="nav-item"><a href="${roomList}">
                <fmt:message key="nav.label.room_list"/></a></li>
            <li class="nav-item"><a href="application.jsp"><fmt:message key="nav.label.application"/></a></li>
        </ul>
        <ul class="nav-right">
            <li class="nav-item"><a href="signup.jsp"><fmt:message key="nav.label.sign_up"/></a></li>
            <li class="nav-item"><a href="login.jsp"><fmt:message key="nav.label.log_in"/></a></li>
            <li class="nav-item"><a href="#"><fmt:message key="nav.label.profile"/></a></li>
        </ul>
    </div>
</div>
</body>
</html>
