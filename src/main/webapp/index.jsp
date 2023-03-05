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

<c:redirect url="/jsp/home.jsp"/>

</body>
</html>