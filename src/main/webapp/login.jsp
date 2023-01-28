<%--
  Created by IntelliJ IDEA.
  User: sasha
  Date: 28.01.2023
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="header">
    <div class="nav">
        <ul class="nav-center">
            <li class="nav-item"><a href="#"><b>Home</b></a></li>
            <li class="nav-item"><a href="#">Room list</a></li>
            <li class="nav-item"><a href="#">Application</a></li>
        </ul>
        <ul class="nav-left">
            <li class="nav-item"><a href="#">Sign up</a></li>
            <li class="nav-item"><a href="#">Log in</a></li>
            <li class="nav-item"><a href="#">Profile</a></li>
        </ul>
    </div>
</div>
<div class="container">
    <div class="main">
        <div class="form-container">
            <form class="register-form" action="controller?command=registerCustomer" method="post">
                <h1 class="form-header">Login info</h1>
                <div class="input-group">
                    <input class="form-input" id="email" name="email" type="email" placeholder="Email">
                </div>
                <div class="input-group">
                    <input class="form-input" id="password" name="password" type="password" placeholder="Password">
                </div>
                <button class="form-button" type="submit">Log in</button>
            </form>
        </div>
    </div>
    <div class="footer">

    </div>
</div>
</body>
</html>
