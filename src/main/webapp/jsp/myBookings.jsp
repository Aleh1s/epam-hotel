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
                <p>Filter</p>
                <div></div>
            </div>
            <form method="get" action="<c:url value="/controller"/>" class="bookings-form">
                <input type="hidden" name="command" value="myBookings"/>
                <div>
                    <label for="filter">
                        Booking status
                    </label>
                    <select id="filter" name="status" class="select-primary">
                        <option value="" disabled ${empty sessionScope.reservationStatus ? 'selected' : ''}>
                            Booking status
                        </option>
                        <option value="1" ${sessionScope.reservationStatus.index eq 1 ? 'selected' : ''}>
                            Pending
                        </option>
                        <option value="2" ${sessionScope.reservationStatus.index eq 2 ? 'selected' : ''}>
                            Confirmed
                        </option>
                        <option value="3" ${sessionScope.reservationStatus.index eq 3 ? 'selected' : ''}>
                            <Cancelled></Cancelled>
                        </option>
                        <option value="4" ${sessionScope.reservationStatus.index eq 4 ? 'selected' : ''}>
                            Payed
                        </option>
                        <option value="5" ${sessionScope.reservationStatus.index eq 5 ? 'selected' : ''}>
                            Expired
                        </option>
                    </select>
                </div>
                <button class="btn-primary" type="submit">Search</button>
            </form>
            <div class="horizontal-delimiter-with-text">
                <p>Reservations</p>
                <div></div>
            </div>
            <c:choose>
                <c:when test="${not empty requestScope.reservationPage.result}">
                    <table class="styled-table">
                        <thead>
                        <tr>
                            <th>Entry date</th>
                            <th>Leaving date</th>
                            <th>Status</th>
                            <th>Total amount</th>
                            <th>Control</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="reservation" items="${requestScope.reservationPage.result}">
                            <tr>
                                <td><fmt:formatDate type="date" value="${reservation.entryDate}"
                                                    pattern="MMM dd, yyyy"/></td>
                                <td><fmt:formatDate type="date" value="${reservation.leavingDate}"
                                                    pattern="MMM dd, yyyy"/></td>
                                <td>${reservation.status}</td>
                                <td>${reservation.totalAmount}$</td>
                                <td class="control">
                                    <c:choose>
                                        <c:when test="${reservation.status.index eq 1 or reservation.status.index eq 2}">
                                            <form action="<c:url value="/controller"/>" method="post">
                                                <input type="hidden" name="command" value="changeReservationStatus">
                                                <input type="hidden" name="reservationId" value="${reservation.id}">
                                                <input type="hidden" name="reservationStatus" value="3">
                                                <button type="submit" class="btn-cancel">Cancel</button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <button disabled class="btn-cancel disabled">Cancel</button>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${reservation.status.index eq 2}">
                                            <form action="<c:url value="/controller"/>" method="get">
                                                <input type="hidden" name="reservationId" value="${reservation.id}">
                                                <input type="hidden" name="command" value="paymentPage"/>
                                                <button type="submit" class="btn-pay">Pay</button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <button disabled class="btn-pay disabled">Pay</button>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${reservation.status.index eq 3}">
                                            <form action="<c:url value="/controller"/>" method="post">
                                                <input type="hidden" name="command" value="changeReservationStatus">
                                                <input type="hidden" name="reservationId" value="${reservation.id}">
                                                <input type="hidden" name="reservationStatus" value="6">
                                                <button type="submit" class="btn-trash">Remove</button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <button disabled class="btn-trash disabled">Remove</button>
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
