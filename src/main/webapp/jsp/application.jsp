<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Application</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
    <div class="main">
        <div class="form-container">
            <form class="custom-form" action="controller?command=application" method="post">
                <h1 class="form-header"><fmt:message key="application.info"/></h1>
                <div class="input-group">
                    <fmt:message var="numberOfGuests" key="number.of.guests"/>
                    <input min="1" max="10" class="form-input" id="number-of-guests"
                           name="numberOfGuests" type="number" placeholder="${numberOfGuests}">
                </div>
                <div class="input-group">
                    <label for="roomClass">
                        <fmt:message key="class"/>
                        <select class="form-input" name="roomClass" id="roomClass">
                            <option value="STANDARD"><fmt:message key="standard"/></option>
                            <option value="SUPERIOR"><fmt:message key="superior"/></option>
                            <option value="FAMILY"><fmt:message key="family"/></option>
                            <option value="BUSINESS"><fmt:message key="business"/></option>
                            <option value="PRESIDENT"><fmt:message key="president"/></option>
                        </select>
                    </label>
                </div>
                <div class="input-group">
                    <label for="date-of-entry">
                        <fmt:message key="entry.date"/>
                        <input id="date-of-entry" class="form-input" name="dateOfEntry" type="date"
                               max="2024-01-01">
                    </label>
                    <label for="date-of-leaving">
                        <fmt:message key="leaving.date"/>
                        <input id="date-of-leaving" class="form-input" name="dateOfLeaving" type="date"
                               max="2024-01-01">
                    </label>
                </div>
                <button class="form-button" type="submit"><fmt:message key="make.an.application"/></button>
            </form>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="../js/script.js"></script>
</body>
</html>
