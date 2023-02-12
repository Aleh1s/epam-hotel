<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Book</title>
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
        <div class="main-container">
            <div class="book-container">
                <form method="post" class="book-form" action="<c:url value="/controller"/>">
                    <h1 class="form-header"><fmt:message key="booking.info"/></h1>
                    <input type="hidden" name="command" value="book"/>
                    <div class="book-form-center">
                        <div class="input-group">
                            <label for="date-of-entry">
                                <fmt:message key="entry.date"/>
                                <input id="date-of-entry" class="form-input" name="entryDate" type="date"
                                       max="2024-01-01" required>
                            </label>
                        </div>
                        <div class="input-group">
                            <label for="date-of-leaving">
                                <fmt:message key="leaving.date"/>
                                <input id="date-of-leaving" class="form-input" name="leavingDate" type="date"
                                       max="2024-01-01" required>
                            </label>
                        </div>
                        <button type="submit" class="book-button"><fmt:message key="book"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
