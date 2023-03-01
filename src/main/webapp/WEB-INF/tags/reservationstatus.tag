<%@ attribute name="status" type="ua.aleh1s.hotelepam.model.entity.ReservationStatus" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="reservationIndex" value="${status.index}"/>
<c:choose>
    <c:when test="${reservationIndex eq 1}">
        <p style="color: #666666">Needs confirmation</p>
    </c:when>
    <c:when test="${reservationIndex eq 2}">
        <p style="color: #3c85ec">Needs payment</p>
    </c:when>
    <c:when test="${reservationIndex eq 3}">
        <p style="color: green">Payed</p>
    </c:when>
    <c:when test="${reservationIndex eq 4}">
        <p style="color: red">Canceled</p>
    </c:when>
</c:choose>