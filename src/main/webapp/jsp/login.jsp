<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<c:import url="header.jsp"/>

<c:if test="${not empty requestScope.errorMessage}">
    <div class="error-container">
        <p>${requestScope.errorMessage}</p>
    </div>
</c:if>

<div class="container">
    <div class="main">
        <div class="form-container">
            <form class="custom-form" action="<c:url value="/controller?command=login"/>" method="post">
                <h1 class="form-header"><fmt:message key="login.info"/></h1>

                <div class="input-group">
                    <fmt:message var="email" key="email"/>
                    <input class="form-input" id="email" name="email" type="email" placeholder="${email}" required>
                </div>
                <div class="input-group">
                    <fmt:message var="password" key="password"/>
                    <input class="form-input" id="password" name="password" type="password" placeholder="${password}" required>
                </div>
                <button class="form-button" type="submit"><fmt:message key="log.in"/></button>
            </form>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
