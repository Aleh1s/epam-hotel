<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>My bookings</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="component/header.jsp"/>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="horizontal-delimiter-with-text">
                <p>Reservations</p>
                <div></div>
            </div>
            <c:choose>
                <c:when test="${not empty requestScope.reservationPage.result}">
                    <table class="styled-table">
                        <thead>
                        <tr>
                            <th>Check-in</th>
                            <th>Check-out</th>
                            <th>Total amount</th>
                            <th>Status</th>
                            <th>Control</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="reservation" items="${requestScope.reservationPage.result}">
                            <tr ${reservation.status.index eq 4 ? 'class="disabled"' : ''}>
                                <td><fmt:formatDate type="date" value="${reservation.checkIn}"
                                                    pattern="MMM dd, yyyy"/></td>
                                <td><fmt:formatDate type="date" value="${reservation.checkOut}"
                                                    pattern="MMM dd, yyyy"/></td>
                                <td>$ ${reservation.totalAmount}</td>
                                <td>
                                    <st:reservationstatus status="${reservation.status}"/>
                                </td>
                                <td class="control">
                                    <c:choose>
                                        <c:when test="${reservation.status.index eq 2}">
                                            <form action="<c:url value="/controller"/>" method="post">
                                                <input type="hidden" name="command" value="payReservation"/>
                                                <input type="hidden" name="reservationId" value="${reservation.id}">
                                                <button type="submit" class="btn-pay">Pay</button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <button disabled class="btn-pay disabled">Pay</button>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${reservation.status.index eq 3}">
                                            <form action="<c:url value="/controller"/>" method="get">
                                                <input type="hidden" name="command" value="downloadReservationPdf"/>
                                                <input type="hidden" name="reservationId" value="${reservation.id}">
                                                <button type="submit" class="btn-accept">Download Pdf</button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <button disabled class="btn-accept disabled">Download Pdf</button>
                                        </c:otherwise>
                                    </c:choose>
                                    <form action="<c:url value="/controller"/>" method="get">
                                        <input type="hidden" name="command" value="viewRoom">
                                        <input type="hidden" name="roomNumber" value="${reservation.roomNumber}">
                                        <button type="submit" class="btn-view">View room</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <tags:notfount/>
                </c:otherwise>
            </c:choose>
            <tags:pagination pagesNumber="${requestScope.pagesNumber}" currPage="${requestScope.currPage}"
                             command="myBookings"/>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
