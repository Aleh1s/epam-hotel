<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="profile-header">
                <div class="profile-image">
                    <img src="https://www.w3schools.com/howto/img_avatar.png" alt="profile image">
                </div>
                <div class="i18n">
                    <form action="<c:url value="/controller"/>" method="post" class="i18n-ua">
                        <input type="hidden" name="command" value="i18n">
                        <input type="hidden" name="lang" value="ua">
                        <button class="i18n-ua-button" type="submit"></button>
                    </form>
                    <span class="vertical-delimiter"></span>
                    <form action="<c:url value="/controller"/>" method="post" class="i18n-en">
                        <input type="hidden" name="command" value="i18n">
                        <input type="hidden" name="lang" value="en">
                        <button class="i18n-en-button" type="submit"></button>
                    </form>
                </div>
                <div class="header-user-info">
                    <h2>Oleksandr Palamarchuk</h2>
                    <p>Customer</p>
                </div>
                <div class="profile-bookings">
                    <p><fmt:message key="number.of.booking"/></p>
                    <h2>12</h2>
                </div>
            </div>
            <div class="profile-body">
                <div class="body-user-info">
                    <div class="horizontal-delimiter-with-text">
                        <p><fmt:message key="contact.info"/></p>
                        <div></div>
                    </div>
                    <table class="profile-info-table">
                        <tbody>
                            <tr class="active">
                                <td class="table-key"><fmt:message key="email"/>:</td>
                                <td class="table-value">sashap740@gmail.com</td>
                            </tr>
                            <tr>
                                <td class="table-key"><fmt:message key="first.name"/>:</td>
                                <td class="table-value">Oleksandr</td>
                            </tr>
                            <tr>
                                <td class="table-key"><fmt:message key="last.name"/>:</td>
                                <td class="table-value">Palamarchuk</td>
                            </tr>
                            <tr class="active">
                                <td class="table-key"><fmt:message key="phone"/>:</td>
                                <td class="table-value">+38 (068) 663-2241</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="profile-request-list">
                    <div class="horizontal-delimiter-with-text">
                        <p><fmt:message key="request.list"/></p>
                        <div></div>
                    </div>
                    <table class="styled-table">
                        <thead>
                        <tr>
                            <th><fmt:message key="room.class"/></th>
                            <th><fmt:message key="room.price"/></th>
                            <th><fmt:message key="beds.number"/></th>
                            <th><fmt:message key="persons.number"/></th>
                            <th><fmt:message key="view"/></th>
                            <th><fmt:message key="reject"/></th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Superior</td>
                                <td>34$/night</td>
                                <td>3</td>
                                <td>4</td>
                                <td><a href="#"><fmt:message key="view"/></a></td>
                                <td><a href="#"><fmt:message key="reject"/></a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
