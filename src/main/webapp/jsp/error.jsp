<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Server Error</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty requestScope.errorMessage}">
        <h1>${requestScope.errorMessage}</h1>
    </c:when>
    <c:otherwise>
        <h1>Server Error</h1>
    </c:otherwise>
</c:choose>
</body>
</html>
