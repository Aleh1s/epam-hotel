<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Sign up</title>
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
            <form class="custom-form" action="<c:url value="/controller?command=signup"/>" method="post">
                <h1 class="form-header"><fmt:message key="signup.info"/></h1>

                <div class="input-group">
                    <fmt:message var="email" key="email"/>
                    <input class="form-input" id="email" name="email" type="email" placeholder="${email}" required>
                </div>
                <div class="input-group">
                    <fmt:message var="password" key="password"/>
                    <input class="form-input" id="password" name="password" type="password" placeholder="${password}"
                           required>
                </div>
                <div class="input-group">
                    <fmt:message var="firstName" key="first.name"/>
                    <input class="form-input" id="firstName" name="firstName" type="text" placeholder="${firstName}"
                           required>
                </div>
                <div class="input-group">
                    <fmt:message var="lastName" key="last.name"/>
                    <input class="form-input" id="lastName" name="lastName" type="text" placeholder="${lastName}"
                           required>
                </div>
                <div class="input-group">
                    <fmt:message var="phoneNumber" key="phone.number"/>
                    <input class="form-input" id="phoneNumber" name="phoneNumber" type="tel"
                           placeholder="${phoneNumber}" required>
                </div>
                <button class="form-button" type="submit"><fmt:message key="sign.up"/></button>
            </form>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
