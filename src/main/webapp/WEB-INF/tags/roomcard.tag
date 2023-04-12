<%@ attribute name="room" type="ua.aleh1s.hotelepam.model.dto.RoomDto" required="true" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<div class="room-card">
    <div class="room-card-image">
        <img src="http://localhost:8080/controller?command=getRoomImage&roomNumber=${room.number()}"
             alt="Room image">
    </div>
    <div class="room-card-content">
        <ul class="key-value-list">
            <li>
                <div class="key"><fmt:message key="class"/>:</div>
                <div class="value">${room.clazz()}</div>
            </li>
            <li>
                <div class="key"><fmt:message key="persons"/>:</div>
                <div class="value">${room.guests()}</div>
            </li>
            <li>
                <div class="key"><fmt:message key="beds"/>:</div>
                <div class="value">${room.beds()}</div>
            </li>
            <li>
                <div class="key"><fmt:message key="area"/>:</div>
                <div class="value">${room.area()} M<sup>2</sup></div>
            </li>
        </ul>
    </div>
    <div class="room-card-footer">
        <p class="room-price-label">$ ${room.price()}</p>
        <a href="<c:url value="/controller?command=viewRoom&roomNumber=${room.number()}"/>"><fmt:message
                key="view"/></a>
    </div>
</div>