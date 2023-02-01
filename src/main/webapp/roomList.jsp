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
                    <form class="sort-control-form" action="controller?command=roomList">
                        <div class="sort-select-group">
                            <label for="price-select">
                                <fmt:message key="form.price.label"/>
                                <select id="price-select" class="form-input" name="price">
                                    <option><fmt:message key="form.select.price.option.lower-higher"/></option>
                                    <option><fmt:message key="form.price-select.option.higher-lower"/></option>
                                </select>
                            </label>
                            <label for="number-of-persons-select">
                                <fmt:message key="form.number-of-persons-label"/>
                                <select id="number-of-persons-select" class="form-input" name="numberOfPersons">
                                    <option><fmt:message key="form.select.number-of-persons.lower-higher"/></option>
                                    <option><fmt:message key="form.select.number-of-persons.higher-lower"/></option>
                                </select>
                            </label>
                        </div>
                        <div class="sort-select-group">
                            <label for="class-select">
                                <fmt:message key="form.class.label"/>
                                <select id="class-select" class="form-input" name="class">
                                    <option><fmt:message key="form.select.standard"/></option>
                                    <option><fmt:message key="form.select.superior"/></option>
                                    <option><fmt:message key="form.select.family-room"/></option>
                                    <option><fmt:message key="form.select.business"/></option>
                                    <option><fmt:message key="form.select.president"/></option>
                                </select>
                            </label>
                            <label for="status-select">
                                <fmt:message key="form.status.label"/>
                                <select id="status-select" class="form-input" name="status">
                                    <option><fmt:message key="form.select.free"/></option>
                                    <option><fmt:message key="form.select.booked"/></option>
                                    <option><fmt:message key="form.select.busy"/></option>
                                    <option><fmt:message key="form.select.unavailable"/></option>
                                </select>
                            </label>
                        </div>
                        <button type="submit" class="form-button"><fmt:message key="form.button.sort"/></button>
                    </form>
                </div>
            </div>
            <div class="room-list">
                <div class="room-card">
                    <div class="room-card-image">
                        <img src="https://www.thespruce.com/thmb/2_Q52GK3rayV1wnqm6vyBvgI3Ew=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/put-together-a-perfect-guest-room-1976987-hero-223e3e8f697e4b13b62ad4fe898d492d.jpg"
                             alt="Room image">
                    </div>
                    <div class="room-card-content">
                        <ul class="key-value-list">
                            <li>
                                <div class="key"><fmt:message key="room.card.status"/>:</div>
                                <div class="value">free</div>
                            </li>
                            <li>
                                <div class="key"><fmt:message key="room.card.class"/>:</div>
                                <div class="value">standard</div>
                            </li>
                            <li>
                                <div class="key"><fmt:message key="room.card.persons-number"/>:</div>
                                <div class="value">2</div>
                            </li>
                            <li>
                                <div class="key"><fmt:message key="room.card.area"/>:</div>
                                <div class="value">15 M<sup>2</sup></div>
                            </li>
                        </ul>
                    </div>
                    <div class="room-card-footer">
                        <p class="room-price-label">100$ <fmt:message key="room.card.price.per-night"/></p>
                        <a href="#">View</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>