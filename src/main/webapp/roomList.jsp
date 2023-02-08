<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Room list</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="group">
                <div class="sort-control">
                    <h2 class="form-header"><fmt:message key="form.header.sorting"/></h2>
                    <form action="controller" method="get">
                        <input type="hidden" name="command" value="roomList">
                        <div class="sorting-form-grid-box">
                            <div class="form-section">
                                <div class="section-header">
                                    By price
                                </div>
                                <div class="price-range">
                                    <label for="price-from" class="form-text">
                                        From
                                        <input id="price-from" name="priceFrom" type="number" min="20" max="500"
                                               value="20"
                                               placeholder="From" class="form-input w100 h30 grey-border ml10 p5">
                                    </label>
                                    <label for="price-to" class="ml20 form-text">
                                        To
                                        <input id="price-to" name="priceTo" type="number" min="20" max="500" value="500"
                                               placeholder="To" class="form-input w100 w100 h30 grey-border ml10 p5">
                                    </label>
                                </div>
                            </div>
                            <div class="form-section ml20">
                                <div class="section-header">
                                    By persons number
                                </div>
                                <div class="persons-range">
                                    <label for="persons-from" class="form-text">
                                        From
                                        <input id="persons-from" name="personsFrom" type="number" min="1" max="10"
                                               value="1"
                                               placeholder="From" class="form-input w100 h30 grey-border ml10 p5">
                                    </label>
                                    <label for="persons-to" class="ml20 form-text">
                                        To
                                        <input id="persons-to" name="personsTo" type="number" min="1" max="10"
                                               value="10"
                                               placeholder="To" class="form-input w100 w100 h30 grey-border ml10 p5">
                                    </label>
                                </div>
                            </div>
                            <div class="form-section">
                                <div class="section-header">
                                    By class
                                </div>
                                <div class="class-checkbox">
                                    <div>
                                        <div>
                                            <input id="standard" type="checkbox" name="standard">
                                            <label for="standard" class="checkbox-label">Standard</label>
                                        </div>
                                        <div>
                                            <input id="superior" type="checkbox" name="superior">
                                            <label for="superior" class="checkbox-label">Superior</label>
                                        </div>
                                    </div>
                                    <div class="ml20">
                                        <div>
                                            <input id="family" type="checkbox" name="family">
                                            <label for="family" class="checkbox-label">Family room</label>
                                        </div>
                                        <div>
                                            <input id="business" type="checkbox" name="business">
                                            <label for="business" class="checkbox-label">Business</label>
                                        </div>
                                    </div>
                                    <div class="ml20">
                                        <div>
                                            <input id="president" type="checkbox" name="president">
                                            <label for="president" class="checkbox-label">President</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-section ml20">
                                <div class="section-header">
                                    By status
                                </div>
                                <div class="status-checkbox">
                                    <div>
                                        <div>
                                            <input id="free" type="checkbox" name="free">
                                            <label for="free" class="checkbox-label">Free</label>
                                        </div>
                                        <div>
                                            <input id="booked" type="checkbox" name="booked">
                                            <label for="booked" class="checkbox-label">booked</label>
                                        </div>
                                    </div>
                                    <div class="ml20">
                                        <div>
                                            <input id="busy" type="checkbox" name="busy">
                                            <label for="busy" class="checkbox-label">Busy</label>
                                        </div>
                                        <div>
                                            <input id="unavailable" type="checkbox" name="unavailable">
                                            <label for="unavailable" class="checkbox-label">Unavailable</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-button-aligner">
                            <button type="submit" class="form-button"><fmt:message key="form.button.sort"/></button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="room-list">
                <c:forEach var="room" items="${requestScope.roomList}">
                    <div class="room-card">
                        <div class="room-card-image">
                            <img src="https://www.thespruce.com/thmb/2_Q52GK3rayV1wnqm6vyBvgI3Ew=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/put-together-a-perfect-guest-room-1976987-hero-223e3e8f697e4b13b62ad4fe898d492d.jpg"
                                 alt="Room image">
                        </div>
                        <div class="room-card-content">
                            <ul class="key-value-list">
                                <li>
                                    <div class="key"><fmt:message key="room.card.status"/>:</div>
                                    <div class="value">${room.roomStatus}</div>
                                </li>
                                <li>
                                    <div class="key"><fmt:message key="room.card.class"/>:</div>
                                    <div class="value">${room.roomClass}</div>
                                </li>
                                <li>
                                    <div class="key"><fmt:message key="room.card.persons-number"/>:</div>
                                    <div class="value">${room.personsNumber}</div>
                                </li>
                                <li>
                                    <div class="key"><fmt:message key="room.card.beds-number"/>:</div>
                                    <div class="value">${room.bedsNumber}</div>
                                </li>
                                <li>
                                    <div class="key"><fmt:message key="room.card.area"/>:</div>
                                    <div class="value">${room.area} M<sup>2</sup></div>
                                </li>
                            </ul>
                        </div>
                        <div class="room-card-footer">
                            <p class="room-price-label">${room.price}$ <fmt:message key="room.card.price.per-night"/></p>
                            <a href="#">View</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>