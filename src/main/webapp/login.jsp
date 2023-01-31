<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="ua" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="header">
    <div class="nav">
        <ul class="nav-center">
            <li class="nav-item"><a href="#"><b><fmt:message key="nav.label.home"/></b></a></li>
            <li class="nav-item"><a href="#"><fmt:message key="nav.label.room_list"/></a></li>
            <li class="nav-item"><a href="#"><fmt:message key="nav.label.application"/></a></li>
        </ul>
        <ul class="nav-left">
            <li class="nav-item"><a href="#"><fmt:message key="nav.label.sign_up"/></a></li>
            <li class="nav-item"><a href="#"><fmt:message key="nav.label.log_in"/></a></li>
            <li class="nav-item"><a href="#"><fmt:message key="nav.label.profile"/></a></li>
        </ul>
    </div>
</div>
<div class="container">
    <div class="main">
        <div class="form-container">
            <form class="register-form" action="controller?command=login" method="post">
                <h1 class="form-header"><fmt:message key="form.header.login_info"/></h1>
                <div class="input-group">
                    <fmt:message var="email" key="form.input.email"/>
                    <input class="form-input" id="email" name="email" type="email" placeholder="${email}">
                </div>
                <div class="input-group">
                    <fmt:message var="password" key="form.input.password"/>
                    <input class="form-input" id="password" name="password" type="password" placeholder="${password}">
                </div>
                <button class="form-button" type="submit"><fmt:message key="form.button.log_in"/></button>
            </form>
        </div>
    </div>
    <div class="footer">

    </div>
</div>
</body>
</html>
