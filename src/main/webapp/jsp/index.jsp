<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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