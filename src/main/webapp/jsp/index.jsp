<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>
<html>
<head>
    <title>Index</title>
</head>
<body>

<c:url var="roomList" value="/controller">
    <c:param name="command" value="roomList"/>
    <c:param name="default" value="on"/>
</c:url>

<c:redirect url="${roomList}"/>

</body>
</html>