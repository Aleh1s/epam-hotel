<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
    <div class="main">
        <div class="form-container">
            <form class="custom-form" action="controller?command=login" method="post">
                <h1 class="form-header"><fmt:message key="form.header.login_info"/></h1>
                <div id="error-container" class="error-container">
                    <p id="error-message">${requestScope.get("errorMessage")}</p>
                </div>
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
    <c:import url="footer.jsp"/>
</div>
<script src="script.js"></script>
</body>
</html>
