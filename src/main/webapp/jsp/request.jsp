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
                            <div class="horizontal-delimiter-with-text">
                                <p>Application Details</p>
                                <div></div>
                            </div>
                            <table>
                                <tr class="active">
                                    <td>Guests Number</td>
                                    <td>${requestScope.application.guestsNumber}</td>
                                </tr>
                                <tr>
                                    <td>Room Class</td>
                                    <td>${requestScope.application.roomClass}</td>
                                </tr>
                                <tr>
                                    <td>Entry Date</td>
                                    <td>${requestScope.application.entryDate}</td>
                                </tr>
                                <tr>
                                    <td>Leaving Date</td>
                                    <td>${requestScope.application.leavingDate}</td>
                                </tr>
                            </table>
                        </div>
                        <div>
                            <div class="horizontal-delimiter-with-text">
                                <p>Customer Details</p>
                                <div></div>
                            </div>
                            <table>
                                <tr class="active">
                                    <td>Email</td>
                                    <td>${requestScope.user.email}</td>
                                </tr>
                                <tr>
                                    <td>First Name</td>
                                    <td>${requestScope.user.firstName}</td>
                                </tr>
                                <tr>
                                    <td>Last Name</td>
                                    <td>${requestScope.user.lastName}</td>
                                </tr>
                                <tr>
                                    <td>Phone</td>
                                    <td>${requestScope.user.phoneNumber}</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="horizontal-delimiter-with-text">
                        <p>Send Request</p>
                        <div></div>
                    </div>
                    <form class="request-form" action="<c:url value="/controller"/>" method="post">
                        <input type="hidden" name="command" value="makeRequest">
                        <label for="room-number">
                            <fmt:message key="room.number"/>
                            <input id="room-number" placeholder="<fmt:message key="room.number"/>" type="number"
                                   name="roomNumber"
                                   class="form-input" value="1" min="1" required>
                        </label>
                        <label for="date-of-entry">
                            <fmt:message key="entry.date"/>
                            <input id="date-of-entry" class="form-input" name="entryDate" type="date"
                                   max="2024-01-01" required>
                        </label>
                        <label for="date-of-leaving">
                            <fmt:message key="leaving.date"/>
                            <input id="date-of-leaving" class="form-input" name="leavingDate" type="date"
                                   max="2024-01-01" required>
                        </label>
                        <button type="submit" class="btn-primary"><fmt:message key="send.request"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
