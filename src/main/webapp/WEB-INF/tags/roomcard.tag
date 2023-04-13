<%@ attribute name="room" type="ua.aleh1s.hotelepam.model.dto.RoomDto" required="true" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<div class="card" style="width: 18rem;">
    <img src="<c:url value="/controller?command=getRoomImage&roomNumber=${room.number()}"/>" class="card-img-top" alt="...">
    <div class="card-body">
        <h5 class="card-title"><fmt:message key="room.number"/> ${room.number()}</h5>
        <p class="card-text">
            <c:choose>
                <c:when test="${room.description().length() > 50}">
                    ${room.description().substring(0, 50)}...
                </c:when>
                <c:otherwise>
                    ${room.description()}
                </c:otherwise>
            </c:choose>
        </p>
    </div>
    <ul class="list-group list-group-flush">
        <li class="list-group-item"><fmt:message key="class"/>: ${room.clazz()}</li>
        <li class="list-group-item"><fmt:message key="persons"/>: ${room.guests()}</li>
        <li class="list-group-item"><fmt:message key="beds"/>: ${room.beds()}</li>
        <li class="list-group-item"><fmt:message key="area"/>: ${room.area()} M<sup>2</sup></li>
    </ul>
    <div class="card-body">
        <p class="room-price-label">$ ${room.price()}</p>
        <a class="card-link" href="<c:url value="/controller?command=viewRoom&roomNumber=${room.number()}"/>"><fmt:message key="view"/></a>
    </div>
</div>