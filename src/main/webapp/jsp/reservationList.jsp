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
            <c:choose>
                <c:when test="${not empty requestScope.reservationPage.result()}">
                    <div class="horizontal-delimiter-with-text">
                        <p><fmt:message key="reservations"/></p>
                        <div></div>
                    </div>
                    <table class="styled-table">
                        <thead>
                        <tr>
                            <th><fmt:message key="room.number"/></th>
                            <th><fmt:message key="check.in"/></th>
                            <th><fmt:message key="check.out"/></th>
                            <th><fmt:message key="total.amount"/></th>
                            <th><fmt:message key="status"/></th>
                            <th><fmt:message key="full.info"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="reservation" items="${requestScope.reservationPage.result()}">
                            <tr>
                                <td>${reservation.roomNumber()}</td>
                                <td>
                                    <fmt:formatDate type="date" value="${reservation.checkIn()}" pattern="dd-MM-yyyy"/>
                                </td>
                                <td>
                                    <fmt:formatDate type="date" value="${reservation.checkOut()}" pattern="dd-MM-yyyy"/>
                                </td>
                                <td>$ ${reservation.totalAmount()}</td>
                                <td>
                                        <st:reservationstatus status="${reservation.status()}"/>
                                </td>
                                <td>
                                    <form method="get" action="<c:url value="/controller"/>">
                                        <input type="hidden" name="command" value="getFullReservation"/>
                                        <input type="hidden" name="reservationId" value="${reservation.id()}"/>
                                        <button type="submit" class="btn-view"><fmt:message key="full.info"/></button>
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
