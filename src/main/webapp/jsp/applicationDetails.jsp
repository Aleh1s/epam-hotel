<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Request</title>
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                                    <td>${requestScope.application.guests}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="room.class"/></td>
                                    <td>${requestScope.application.roomClass}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="check.in"/></td>
                                    <td>${requestScope.application.checkIn}</td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="check.out"/></td>
                                    <td>${requestScope.application.checkOut}</td>
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

                        <c:set var="errors" value="${requestScope.errors}"/>
                        <div class="form-group-sm w-25">
                            <label for="room-number" class="form-label fs-6"><fmt:message key="room.number"/></label>
                            <input id="room-number" placeholder="<fmt:message key="room.number"/>" type="number"
                                   name="roomNumber"
                                   class="form-control" value="1" min="1" required>
                            <tags:fielderror messages="${errors['roomNumber']}"/>
                        </div>
                        <div class="form-group-sm">
                            <label for="date-of-entry" class="form-label fs-6"><fmt:message key="check.in"/></label>
                            <input id="date-of-entry" class="form-control" name="checkIn" type="date"
                                   max="2024-01-01" required>
                            <tags:fielderror messages="${errors['checkIn']}"/>
                            <tags:fielderror messages="${errors['period']}"/>
                        </div>
                        <div class="form-group-sm">
                            <label for="date-of-leaving" class="form-label fs-6"><fmt:message key="check.out"/></label>
                            <input id="date-of-leaving" class="form-control" name="checkOut" type="date"
                                   max="2024-01-01" required>
                            <tags:fielderror messages="${errors['checkOut']}"/>
                            <tags:fielderror messages="${errors['period']}"/>
                        </div>
                        <button type="submit" class="btn-primary"><fmt:message key="request"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="component/footer.jsp"/>
</body>
</html>
