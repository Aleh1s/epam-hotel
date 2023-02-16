<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Confirm booking</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="form-aligner">
                <form method="post" action="<c:url value="/controller"/>" class="confirm-booking-form">
                    <h1 class="form-header">Booking info</h1>
                    <div class="form-center">
                        <table class="info-table">
                            <tr>
                                <td>Entry date:</td>
                                <td>${requestScope.bookInfo.entryDate}</td>
                            </tr>
                            <tr>
                                <td>Leaving date:</td>
                                <td>${requestScope.bookInfo.leavingDate}</td>
                            </tr>
                            <tr>
                                <td>Total amount:</td>
                                <td>${requestScope.bookInfo.totalAmount}$</td>
                            </tr>
                        </table>

                        <input type="hidden" name="command" value="confirmBooking">
                        <input type="hidden" name="roomNumber" value="${requestScope.bookInfo.roomNumber}">
                        <input type="hidden" name="entryDate" value="${requestScope.bookInfo.entryDate}">
                        <input type="hidden" name="leavingDate" value="${requestScope.bookInfo.leavingDate}">
                        <input type="hidden" name="totalAmount" value="${requestScope.bookInfo.totalAmount}">

                        <button type="submit" class="form-button">Confirm</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
