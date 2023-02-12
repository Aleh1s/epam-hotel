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
                    <h2 class="form-header"><fmt:message key="sorting"/></h2>
                    <form action="controller" method="get">
                        <input type="hidden" name="command" value="roomList">
                        <div class="sorting-form-grid-box">
                            <div class="form-section">
                                <div class="section-header">
                                    <fmt:message key="by.price"/>
                                </div>
                                <div class="price-range">
                                    <label for="price-from" class="form-text">
                                        <fmt:message key="from"/>
                                        <input id="price-from" name="priceFrom" type="number" min="20" max="500"
                                               value="${requestScope.params.priceFrom}"
                                               class="form-input w100 h30 grey-border ml10 p5"
                                               placeholder="From" required>
                                    </label>
                                    <label for="price-to" class="ml20 form-text">
                                        <fmt:message key="to"/>
                                        <input id="price-to" name="priceTo" type="number"
                                               min="20"
                                               max="500" value="${requestScope.params.priceTo}"
                                               class="form-input w100 w100 h30 grey-border ml10 p5"
                                               placeholder="To" required>
                                    </label>
                                </div>
                            </div>
                            <div class="form-section ml20">
                                <div class="section-header">
                                    <fmt:message key="by.persons.number"/>
                                </div>
                                <div class="persons-range">
                                    <label for="persons-from" class="form-text">
                                        <fmt:message key="from"/>
                                        <input id="persons-from" name="personsFrom" type="number" min="1" max="10"
                                               value="${requestScope.params.personsFrom}"
                                               placeholder="From" class="form-input w100 h30 grey-border ml10 p5" required>
                                    </label>
                                    <label for="persons-to" class="ml20 form-text">
                                        <fmt:message key="to"/>
                                        <input id="persons-to" name="personsTo" type="number" min="1" max="10"
                                               value="${requestScope.params.personsTo}"
                                               placeholder="To" class="form-input w100 w100 h30 grey-border ml10 p5" required>
                                    </label>
                                </div>
                            </div>
                            <div class="form-section">
                                <div class="section-header">
                                    <fmt:message key="by.class"/>
                                </div>
                                <div class="class-checkbox">
                                    <div>
                                        <div>
                                            <input id="standard" type="checkbox" name="standard"
                                            ${requestScope.params.standard ? "checked" : ""}>
                                            <label for="standard" class="checkbox-label"><fmt:message key="standard"/></label>
                                        </div>
                                        <div>
                                            <input id="superior" type="checkbox" name="superior"
                                            ${requestScope.params.superior ? "checked" : ""}>
                                            <label for="superior" class="checkbox-label"><fmt:message key="superior"/></label>
                                        </div>
                                    </div>
                                    <div class="ml20">
                                        <div>
                                            <input id="family" type="checkbox" name="family"
                                            ${requestScope.params.family ? "checked" : ""}>
                                            <label for="family" class="checkbox-label"><fmt:message key="family"/></label>
                                        </div>
                                        <div>
                                            <input id="business" type="checkbox" name="business"
                                            ${requestScope.params.business ? "checked" : ""}>
                                            <label for="business" class="checkbox-label"><fmt:message key="business"/></label>
                                        </div>
                                    </div>
                                    <div class="ml20">
                                        <div>
                                            <input id="president" type="checkbox" name="president"
                                            ${requestScope.params.president ? "checked" : ""}>
                                            <label for="president" class="checkbox-label"><fmt:message key="president"/></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-section ml20">
                                <div class="section-header">
                                    <fmt:message key="by.status"/>
                                </div>
                                <div class="status-checkbox">
                                    <div>
                                        <div>
                                            <input id="free" type="checkbox" name="free"
                                            ${requestScope.params.free ? "checked" : ""}>
                                            <label for="free" class="checkbox-label"><fmt:message key="free"/></label>
                                        </div>
                                        <div>
                                            <input id="booked" type="checkbox" name="booked"
                                            ${requestScope.params.booked ? "checked" : ""}>
                                            <label for="booked" class="checkbox-label"><fmt:message key="booked"/></label>
                                        </div>
                                    </div>
                                    <div class="ml20">
                                        <div>
                                            <input id="busy" type="checkbox"
                                                   name="busy" ${requestScope.params.busy ? "checked" : ""}>
                                            <label for="busy" class="checkbox-label"><fmt:message key="busy"/></label>
                                        </div>
                                        <div>
                                            <input id="unavailable" type="checkbox" name="unavailable"
                                            ${requestScope.params.unavailable ? "checked" : ""}>
                                            <label for="unavailable" class="checkbox-label"><fmt:message key="unavailable"/></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-button-aligner">
                            <button type="submit" class="form-button"><fmt:message key="search"/></button>
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
                                    <div class="key"><fmt:message key="status"/>:</div>
                                    <div class="value">${room.roomStatus}</div>
                                </li>
                                <li>
                                    <div class="key"><fmt:message key="class"/>:</div>
                                    <div class="value">${room.roomClass}</div>
                                </li>
                                <li>
                                    <div class="key"><fmt:message key="persons.number"/>:</div>
                                    <div class="value">${room.personsNumber}</div>
                                </li>
                                <li>
                                    <div class="key"><fmt:message key="beds.number"/>:</div>
                                    <div class="value">${room.bedsNumber}</div>
                                </li>
                                <li>
                                    <div class="key"><fmt:message key="area"/>:</div>
                                    <div class="value">${room.area} M<sup>2</sup></div>
                                </li>
                            </ul>
                        </div>
                        <div class="room-card-footer">
                            <p class="room-price-label">${room.price}$/<fmt:message
                                    key="night"/></p>
                            <a href="<c:url value="/controller?command=viewRoom&roomNumber=${room.roomNumber}"/>"><fmt:message key="view"/></a>
                        </div>
                    </div>
                </c:forEach>
            </div>
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
                                <a href="<c:url value="/controller?command=roomList&pageNumber=${i}&${requestScope.paramString}"/>">${i}</a>
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