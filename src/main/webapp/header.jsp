<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="header">
    <div class="nav">
        <ul class="nav-center">
            <li class="nav-item"><a href="#"><b><fmt:message key="nav.label.home"/></b></a></li>
            <li class="nav-item"><a href="controller?command=roomList"><fmt:message key="nav.label.room_list"/></a></li>
            <li class="nav-item"><a href="#"><fmt:message key="nav.label.application"/></a></li>
        </ul>
        <ul class="nav-left">
            <li class="nav-item"><a href="#"><fmt:message key="nav.label.sign_up"/></a></li>
            <li class="nav-item"><a href="#"><fmt:message key="nav.label.log_in"/></a></li>
            <li class="nav-item"><a href="#"><fmt:message key="nav.label.profile"/></a></li>
        </ul>
    </div>
</div>
</body>
</html>
