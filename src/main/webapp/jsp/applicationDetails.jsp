<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Request</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="component/header.jsp"/>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="align-center">
                <div class="request-container">
                    <div class="primary-info-container">
                        <div>
                            <fmt:message var="applicationInfo" key="application.info"/>
                            <tags:horizontaldelimiter message="${applicationInfo}"/>
                            <table>
                                <tr class="active">
                                    <td><fmt:message key="guests"/></td>
                                    <td>${requestScope.application.guests()}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="room.class"/></td>
                                    <td>${requestScope.application.roomClass()}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="check.in"/></td>
                                    <td>
                                        <fmt:formatDate type="date" value="${requestScope.application.checkIn()}"
                                                        pattern="dd-MM-yyyy"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="check.out"/></td>
                                    <td>
                                        <fmt:formatDate type="date" value="${requestScope.application.checkOut()}"
                                                        pattern="dd-MM-yyyy"/>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div>
                            <fmt:message var="customerInfo" key="customer.info"/>
                            <tags:horizontaldelimiter message="${customerInfo}"/>
                            <table>
                                <tr class="active">
                                    <td><fmt:message key="email"/></td>
                                    <td>${requestScope.user.email()}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="first.name"/></td>
                                    <td>${requestScope.user.firstName()}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="last.name"/></td>
                                    <td>${requestScope.user.lastName()}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="phone"/></td>
                                    <td>${requestScope.user.phoneNumber()}</td>
                                </tr>
                            </table>
                        </div>
                    </div>

                    <fmt:message var="requestMessage" key="request"/>
                    <tags:horizontaldelimiter message="${requestMessage}"/>

                    <form class="request-form" action="<c:url value="/controller"/>" method="post">
                        <input type="hidden" name="command" value="makeRequest">
                        <label for="room-number">
                            <fmt:message key="room.number"/>
                            <input id="room-number" placeholder="<fmt:message key="room.number"/>" type="number"
                                   name="roomNumber"
                                   class="custom-form-input" value="1" min="1" required>
                        </label>
                        <label for="date-of-entry">
                            <fmt:message key="check.in"/>
                            <input id="date-of-entry" class="custom-form-input" name="checkIn" type="date"
                                   max="2024-01-01" required>
                        </label>
                        <label for="date-of-leaving">
                            <fmt:message key="check.out"/>
                            <input id="date-of-leaving" class="custom-form-input" name="checkOut" type="date"
                                   max="2024-01-01" required>
                        </label>
                        <button type="submit" class="btn-primary"><fmt:message key="request"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
