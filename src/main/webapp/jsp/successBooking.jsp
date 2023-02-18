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
                        <h1>Booking Successful!</h1>
                        <p>Your booking will be processed by manager! Details of booking are included below.
                            Reservation must be paid within two days. If the bill is not paid, the reservation is
                            automatically withdrawn.</p>
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
                                <c:remove var="reservationTotalAmount" scope="session"/>
                            </tr>
                            <tr>
                                <td>Entry Date</td>
                                <td>${sessionScope.reservationEntryDate}</td>
                                <c:remove var="reservationEntryDate" scope="session"/>
                            </tr>
                            <tr>
                                <td>Leaving Date</td>
                                <td>${sessionScope.reservationLeavingDate}</td>
                                <c:remove var="reservationLeavingDate" scope="session"/>
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
