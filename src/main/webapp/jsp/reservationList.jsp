<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${requestScope.lang}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Reservation list</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="horizontal-delimiter-with-text">
                <p>Filter</p>
                <div></div>
            </div>
            <form method="get" action="<c:url value="/controller"/>" class="bookings-form">
                <input type="hidden" name="command" value="reservationList"/>
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
                            Cancelled
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
            <c:if test="${not empty requestScope.reservationPage.result}">
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
                            <td>${reservation.entryDate}</td>
                            <td>${reservation.leavingDate}</td>
                            <td>${reservation.status}</td>
                            <td>${reservation.totalAmount}$</td>
                            <td class="control">
                                <c:choose>
                                    <c:when test="${reservation.status.index eq 1}">
                                        <form action="<c:url value="/controller"/>" method="post">
                                            <input type="hidden" name="command" value="changeReservationStatus">
                                            <input type="hidden" name="reservationStatus" value="2">
                                            <input type="hidden" name="reservationId" value="${reservation.id}">
                                            <button type="submit" class="btn-accept">Confirm</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" class="btn-accept disabled">Confirm</button>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${reservation.status.index eq 1}">
                                        <form action="<c:url value="/controller"/>" method="post">
                                            <input type="hidden" name="command" value="changeReservationStatus">
                                            <input type="hidden" name="reservationStatus" value="3">
                                            <input type="hidden" name="reservationId" value="${reservation.id}">
                                            <button type="submit" class="btn-trash">Cancel</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" class="btn-trash disabled" disabled>Cancel</button>
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
            </c:if>
            <div class="pagination">
                <c:forEach var="i" begin="1" end="${requestScope.pagesNumber}">
                    <c:choose>
                        <c:when test="${requestScope.currPage eq i}">
                            <div class="pagination-elem active">
                                <a href="#">${i}</a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="pagination-elem">
                                <a href="<c:url value="/controller?command=reservationList&pageNumber=${i}&${requestScope.paramString}"/>">${i}</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
