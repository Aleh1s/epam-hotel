<%@ attribute name="room" type="ua.aleh1s.hotelepam.model.dto.RoomDto" required="true" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<div class="room-card">
    <div class="room-card-image">
        <img src="https://www.thespruce.com/thmb/2_Q52GK3rayV1wnqm6vyBvgI3Ew=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/put-together-a-perfect-guest-room-1976987-hero-223e3e8f697e4b13b62ad4fe898d492d.jpg"
             alt="Room image">
    </div>
    <div class="room-card-content">
        <ul class="key-value-list">
            <li>
                <div class="key"><fmt:message key="status"/>:</div>
                <div class="value">${room.roomStatus()}</div>
            </li>
            <li>
                <div class="key"><fmt:message key="class"/>:</div>
                <div class="value">${room.roomClass()}</div>
            </li>
            <li>
                <div class="key"><fmt:message key="persons"/>:</div>
                <div class="value">${room.personsNumber()}</div>
            </li>
            <li>
                <div class="key"><fmt:message key="beds"/>:</div>
                <div class="value">${room.bedsNumber()}</div>
            </li>
            <li>
                <div class="key"><fmt:message key="area"/>:</div>
                <div class="value">${room.area()} M<sup>2</sup></div>
            </li>
        </ul>
    </div>
    <div class="room-card-footer">
        <p class="room-price-label">$ ${room.price()}</p>
        <a href="<c:url value="/controller?command=viewRoom&roomNumber=${room.roomNumber()}"/>"><fmt:message
                key="view"/></a>
    </div>
</div>