<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form action="hello-servlet" method="post">
    <input name="login" type="text"/>
    <input name="password" type="password"/>
    <input name="timezone" type="text"/>
    <input name="locale" type="text"/>
    <input name="role" type="text">
    <input type="submit" value="Go!">
</form>
</body>
</html>