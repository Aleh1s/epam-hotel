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
            <form class="reservation-list-form" method="get" action="<c:url value="/controller"/>">
                <input type="hidden" name="command" value="reservationList">
                <label>
                    Pending
                    <input type="checkbox" name="pending" ${requestScope.params.pending ? "checked" : ""}>
                </label>
                <label>
                    Confirmed
                    <input type="checkbox" name="confirmed" ${requestScope.params.confirmed ? "checked" : ""}>
                </label>
                <label>
                    Cancelled
                    <input type="checkbox" name="cancelled" ${requestScope.params.cancelled ? "checked" : ""}>
                </label>
                <label>
                    Payed
                    <input type="checkbox" name="payed" ${requestScope.params.payed ? "checked" : ""}>
                </label>
                <label>
                    Expired
                    <input type="checkbox" name="expired" ${requestScope.params.expired ? "checked" : ""}>
                </label>
                <button class="form-button" type="submit">Search</button>
            </form>

            <c:if test="${not empty requestScope.reservationList}">
                <table class="styled-table">
                    <thead>
                    <tr>
                        <th>Room number</th>
                        <th>Entry date</th>
                        <th>Leaving date</th>
                        <th>Status</th>
                        <th>Total amount</th>
                        <th>Control</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="reservation" items="${requestScope.reservationList}">
                        <tr>
                            <td>${reservation.roomNumber}</td>
                            <td>${reservation.entryDate}</td>
                            <td>${reservation.leavingDate}</td>
                            <td>${reservation.status}</td>
                            <td>${reservation.totalAmount}$</td>
                            <td>
                                <button>Con</button>
                                <button>Can</button>
                                <button>Vie</button>
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
