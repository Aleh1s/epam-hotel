<%@ attribute name="room" type="ua.aleh1s.hotelepam.model.dto.RoomDto" required="true" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<div class="room-block">
    <div class="room-block-image">
        <img src="https://www.thespruce.com/thmb/2_Q52GK3rayV1wnqm6vyBvgI3Ew=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/put-together-a-perfect-guest-room-1976987-hero-223e3e8f697e4b13b62ad4fe898d492d.jpg"
             alt="">
    </div>
    <div class="room-block-content">
        <div class="room-block-header">
            <div class="title">
                <h4>Number: ${room.number()}. ${room.title()}</h4>
            </div>
            <div class="status">
                ${room.isUnavailable() ?
                 '<p style="color:red">Unavailable</p>'
                 : '<p style="color:green">Available</p>'}
            </div>
        </div>
        <div class="description">
            <p>${room.description()}</p>
        </div>
        <div class="properties">
            <fmt:message var="beds" key="beds"/>
            <fmt:message var="clazz" key="class"/>
            <fmt:message var="guests" key="guests"/>
            <fmt:message var="area" key="area"/>
            <fmt:message var="price" key="price"/>

            <c:set var="areaValue">
                 ${room.area()} M<sup>2</sup>
            </c:set>

            <c:set var="priceValue">
                $${room.price()}/<fmt:message key="night"/>
            </c:set>

            <tags:propertyelement name="${clazz}" value="${room.clazz().name().toLowerCase()}"/>
            <tags:propertyelement name="${beds}" value="${room.beds()}"/>
            <tags:propertyelement name="${guests}" value="${room.guests()}"/>
            <tags:propertyelement name="${area}" value="${areaValue}"/>
            <tags:propertyelement name="${price}" value="${priceValue}"/>
        </div>
        <div class="control">
            <form action="<c:url value="/controller"/>" method="get">
                <input type="hidden" name="command" value="getRoomEditor">
                <input type="hidden" name="number" value="${room.number()}">
                <button type="submit" class="btn-primary"><fmt:message key="edit"/></button>
            </form>
            <c:choose>
                <c:when test="${room.isUnavailable()}">
                    <form action="<c:url value="/controller"/>" method="post">
                        <input type="hidden" name="command" value="changeRoomAvailability">
                        <input type="hidden" name="number" value="${room.number()}">
                        <input type="hidden" name="value" value="false">
                        <button type="submit" class="btn-success"><fmt:message key="set.available"/></button>
                    </form>
                </c:when>
                <c:otherwise>
                    <form action="<c:url value="/controller"/>" method="post">
                        <input type="hidden" name="command" value="changeRoomAvailability">
                        <input type="hidden" name="number" value="${room.number()}">
                        <input type="hidden" name="value" value="true">
                        <button type="submit" class="btn-danger"><fmt:message key="set.unavailable"/></button>
                    </form>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</div>