<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>My bookings</title>
</head>
<body>
<c:import url="header.jsp"/>

<c:if test="${not empty requestScope.errorMessage}">
    <div class="error-container">
        <p>${requestScope.errorMessage}</p>
    </div>
</c:if>

<div class="container">
    <div class="main">
        <div class="main-container">
            <c:if test="${not empty requestScope.reservationPage.result}">
                <table class="styled-table">
                    <thead>
                    <tr>
                        <th>Room number</th>
                        <th>Entry date</th>
                        <th>Leaving date</th>
                        <th>Expired at</th>
                        <th>Payed at</th>
                        <th>Status</th>
                        <th>Total amount</th>
                        <th>Control</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="reservation" items="${requestScope.reservationPage.result}">
                        <tr>
                            <td>${reservation.roomNumber}</td>
                            <td><fmt:formatDate type="date" value="${reservation.entryDate}"
                                                pattern="MMM dd, yyyy"/></td>
                            <td><fmt:formatDate type="date" value="${reservation.leavingDate}"
                                                pattern="MMM dd, yyyy"/></td>
                            <td><fmt:formatDate type="both" value="${reservation.expiredAt}"
                                                pattern="MMM dd, yyyy HH:mm:ss"/></td>
                            <td><fmt:formatDate type="both" value="${reservation.payedAt}"
                                                pattern="MMM dd, yyyy HH:mm:ss"/></td>
                            <td>${reservation.status}</td>
                            <td>${reservation.totalAmount}$</td>
                            <td>
                                <c:choose>
                                    <c:when test="${reservation.status.index eq 1}">
                                        <form action="<c:url value="/controller"/>" method="post">
                                            <input type="hidden" name="command" value="changeReservationStatus">
                                            <input type="hidden" name="reservationId" value="${reservation.id}">
                                            <input type="hidden" name="reservationStatus" value="3">
                                            <button type="submit">Can</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <button disabled>Can</button>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${reservation.status.index eq 2}">
                                        <form action="<c:url value="/controller"/>" method="get">
                                            <input type="hidden" name="reservationId" value="${reservation.id}">
                                            <input type="hidden" name="command" value="paymentPage"/>
                                            <button type="submit">Pay</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <button disabled>Pay</button>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${reservation.status.index eq 3}">
                                        <form action="<c:url value="/controller"/>" method="post">
                                            <input type="hidden" name="command" value="changeReservationStatus">
                                            <input type="hidden" name="reservationId" value="${reservation.id}">
                                            <input type="hidden" name="reservationStatus" value="6">
                                            <button>Rem</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <button disabled>Rem</button>
                                    </c:otherwise>
                                </c:choose>
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
                                <a href="<c:url value="/controller?command=myBookings&pageNumber=${i}"/>">${i}</a>
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
