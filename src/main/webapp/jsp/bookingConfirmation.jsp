<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Successful Confirmation</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="component/header.jsp"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="align-center">
                <div class="booking-confirmation-container">
                    <div class="success-mark-container">
                        <img src="../img/success_mark.png" alt="Success mark">
                    </div>
                    <h1>Booking is confirmed!</h1>
                    <p>Your booking is successfully confirmed. Reservation must be paid within two days. If the bill is
                        not paid, the reservation is automatically withdrawn.</p>
                    <form action="<c:url value="/controller"/>" method="get">
                        <input type="hidden" name="command" value="roomList">
                        <button type="submit" class="btn-primary">Go home</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>