<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="component/header.jsp"/>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="profile-header">
                <div class="profile-image">
                    <img src="https://www.w3schools.com/howto/img_avatar.png" alt="profile image">
                </div>
                <div class="profile-up-bar">
                    <c:if test="${sessionScope.role eq 'CUSTOMER'}">
                        <a class="profile-link"
                           href="<c:url value="/controller?command=myBookings"/>"><fmt:message
                                key="my.bookings"/></a>
                        <form action="<c:url value="/controller"/>" method="post">
                            <input type="hidden" name="command" value="topUpAccount"/>
                            <input class="profile-amount" type="number" min="0" max="10000" name="amount" value="0"
                                   required/>
                            <button type="submit" class="btn-primary"><fmt:message key="top.up"/></button>
                        </form>
                        <form action="<c:url value="/controller"/>" method="post">
                            <input type="hidden" name="command" value="logOut"/>
                            <button type="submit" class="btn-danger"><fmt:message key="log.out"/></button>
                        </form>
                    </c:if>
                </div>
                <div class="header-user-info">
                    <h2>${requestScope.userDto.firstName()} ${requestScope.userDto.lastName()}</h2>
                    <p>${requestScope.userDto.role()}</p>
                </div>
                <c:if test="${sessionScope.role eq 'CUSTOMER'}">
                    <div class="profile-account">
                        <p><fmt:message key="account"/></p>
                        <h2>$ ${requestScope.userDto.account()}</h2>
                    </div>
                </c:if>
            </div>
            <div class="profile-body">
                <div class="body-user-info">
                    <div class="horizontal-delimiter-with-text">
                        <p><fmt:message key="contact.info"/></p>
                        <div></div>
                    </div>
                    <table class="key-value-table">
                        <tbody>
                        <tr class="active">
                            <td class="table-key"><fmt:message key="email"/>:</td>
                            <td class="table-value">${requestScope.userDto.email()}</td>
                        </tr>
                        <tr>
                            <td class="table-key"><fmt:message key="first.name"/>:</td>
                            <td class="table-value">${requestScope.userDto.firstName()}</td>
                        </tr>
                        <tr>
                            <td class="table-key"><fmt:message key="last.name"/>:</td>
                            <td class="table-value">${requestScope.userDto.lastName()}</td>
                        </tr>
                        <tr class="active">
                            <td class="table-key"><fmt:message key="phone"/>:</td>
                            <td class="table-value">${requestScope.userDto.phoneNumber()}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <c:if test="${sessionScope.role eq 'CUSTOMER'}">
                    <div class="profile-request-list">
                        <div class="horizontal-delimiter-with-text">
                            <p><fmt:message key="request.list"/></p>
                            <div></div>
                        </div>
                        <c:if test="${not empty requestScope.requestDtoPage.result()}">
                            <table class="styled-table">
                                <thead>
                                <tr>
                                    <th><fmt:message key="check.in"/></th>
                                    <th><fmt:message key="check.out"/></th>
                                    <th><fmt:message key="total.amount"/></th>
                                    <th><fmt:message key="control"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="request" items="${requestScope.requestDtoPage.result()}">
                                    <tr>
                                        <td>
                                            <fmt:formatDate type="date" value="${request.checkIn}"
                                                            pattern="dd-MM-yyyy"/>
                                        </td>
                                        <td>
                                            <fmt:formatDate type="date" value="${request.checkOut}"
                                                            pattern="dd-MM-yyyy"/>
                                        </td>
                                        <td>$ ${request.totalAmount()}</td>
                                        <td class="control">
                                            <form action="<c:url value="/controller"/>" method="get">
                                                <input type="hidden" name="command" value="viewRoom">
                                                <input type="hidden" name="roomNumber" value="${request.roomNumber()}">
                                                <button class="btn-view" type="submit"><fmt:message key="view.room"/></button>
                                            </form>
                                            <form action="<c:url value="/controller"/>" method="post">
                                                <input type="hidden" name="command" value="rejectRequest">
                                                <input type="hidden" name="requestId" value="${request.id()}">
                                                <button class="btn-trash" type="submit"><fmt:message key="reject"/></button>
                                            </form>
                                            <form action="<c:url value="/controller"/>" method="post">
                                                <input type="hidden" name="command" value="confirmRequest">
                                                <input type="hidden" name="requestId" value="${request.id()}">
                                                <button class="btn-accept" type="submit"><fmt:message key="book"/></button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
