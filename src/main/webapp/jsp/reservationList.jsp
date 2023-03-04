<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Reservation list</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="component/header.jsp"/>
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
                            <th>Room Number</th>
                            <th>Check-in</th>
                            <th>Check-out</th>
                            <th>Total amount</th>
                            <th>Status</th>
                            <th>Full Info</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="reservation" items="${requestScope.reservationPage.result}">
                            <tr>
                                <td>${reservation.roomNumber}</td>
                                <td>${reservation.checkIn}</td>
                                <td>${reservation.checkOut}</td>
                                <td>$ ${reservation.totalAmount}</td>
                                <td>
                                        <st:reservationstatus status="${reservation.status}"/>
                                </td>
                                <td>
                                    <form method="get" action="<c:url value="/controller"/>">
                                        <input type="hidden" name="command" value="getFullReservation"/>
                                        <input type="hidden" name="reservationId" value="${reservation.id}"/>
                                        <button type="submit" class="btn-view">Full Info</button>
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
                             command="reservationList"/>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
