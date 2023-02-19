<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<html>
<head>
    <title>Success booking</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="component/header.jsp"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="align-center">
                <div class="success-grid-container">
                    <div class="success-image-container">
                        <img src="../img/success_mark.png" alt="Success mark">
                    </div>
                    <div class="congratulation-container">
                        <h1>Reservation needs confirmation!</h1>
                        <p>You will receive email to confirm your reservation. Confirmation is valid 15 minutes.</p>
                    </div>
                    <div class="horizontal-delimiter-with-text">
                        <p>Booking info</p>
                        <div></div>
                    </div>
                    <div class="primary-info-container">
                        <table>
                            <tr class="active">
                                <td>Total Amount</td>
                                <td>$ ${sessionScope.reservationTotalAmount}</td>
                            </tr>
                            <tr>
                                <td>Check-in</td>
                                <td>${sessionScope.reservationCheckIn}</td>
                            </tr>
                            <tr>
                                <td>Check-out</td>
                                <td>${sessionScope.reservationCheckOut}</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <form class="go-home-form" method="get" action="<c:url value="/controller"/>">
                    <input type="hidden" name="command" value="roomList">
                    <input type="hidden" name="default" value="on">
                    <button type="submit" class="btn-primary">Go Home</button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
