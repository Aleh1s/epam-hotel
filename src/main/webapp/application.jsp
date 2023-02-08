<%--
  Created by IntelliJ IDEA.
  User: sasha
  Date: 02.02.2023
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
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
                <h1 class="form-header"><fmt:message key="form.header.application-info"/></h1>
                <div class="input-group">
                    <fmt:message var="numberOfGuests" key="form.input.number-of-guests"/>
                    <input min="1" max="10" class="form-input" id="number-of-guests"
                           name="numberOfGuests" type="number" placeholder="${numberOfGuests}">
                </div>
                <div class="input-group">
                    <label for="roomClass">
                        <fmt:message key="form.label.class"/>
                        <select class="form-input" name="roomClass" id="roomClass">
                            <option value="STANDARD"><fmt:message key="form.select.standard"/></option>
                            <option value="SUPERIOR"><fmt:message key="form.select.superior"/></option>
                            <option value="FAMILY"><fmt:message key="form.select.family-room"/></option>
                            <option value="BUSINESS"><fmt:message key="form.select.business"/></option>
                            <option value="PRESIDENT"><fmt:message key="form.select.president"/></option>
                        </select>
                    </label>
                </div>
                <div class="input-group">
                    <label for="date-of-entry">
                        <fmt:message key="form.label.from"/>
                        <input id="date-of-entry" class="form-input" name="dateOfEntry" type="date"
                               max="2024-01-01">
                    </label>
                    <label for="date-of-leaving">
                        <fmt:message key="form.label.to"/>
                        <input id="date-of-leaving" class="form-input" name="dateOfLeaving" type="date"
                               max="2024-01-01">
                    </label>
                </div>
                <button class="form-button" type="submit"><fmt:message key="form.button.make-an-application"/></button>
            </form>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="script.js"></script>
</body>
</html>
