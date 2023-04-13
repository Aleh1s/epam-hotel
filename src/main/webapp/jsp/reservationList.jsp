<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Reservation list</title>
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<c:import url="component/header.jsp"/>
<div class="container">
    <div style="height: 100%" class="main">
        <div class="main-container">
            <c:choose>
                <c:when test="${not empty requestScope.reservationPage.result()}">
                    <fmt:message var="reservationsMessage" key="reservations"/>
                    <tags:horizontaldelimiter message="${reservationsMessage}"/>
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
