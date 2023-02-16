<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Room</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="component/header.jsp"/>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="room-grid-container">
                <div class="room-image-container">
                    <img src="https://www.thespruce.com/thmb/2_Q52GK3rayV1wnqm6vyBvgI3Ew=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/put-together-a-perfect-guest-room-1976987-hero-223e3e8f697e4b13b62ad4fe898d492d.jpg"
                         alt="Room image">
                </div>
                <div class="room-properties-container">
                    <div class="room-property-list">
                        <div class="room-key-value-list">
                            <c:if test="${not empty sessionScope.role and sessionScope.role eq 'MANAGER'}">
                                <div>
                                    <div class="key"><fmt:message key="room.number"/>:</div>
                                    <div class="value">${requestScope.roomDto.roomNumber}</div>
                                </div>
                            </c:if>
                            <div>
                                <div class="key"><fmt:message key="status"/>:</div>
                                <div class="value">${requestScope.roomDto.roomStatus}</div>
                            </div>
                            <div>
                                <div class="key"><fmt:message key="class"/>:</div>
                                <div class="value">${requestScope.roomDto.roomClass}</div>
                            </div>
                            <div>
                                <div class="key"><fmt:message key="persons.number"/>:</div>
                                <div class="value">${requestScope.roomDto.personsNumber}</div>
                            </div>
                            <div>
                                <div class="key"><fmt:message key="beds.number"/>:</div>
                                <div class="value">${requestScope.roomDto.bedsNumber}</div>
                            </div>
                            <div>
                                <div class="key"><fmt:message key="area"/>:</div>
                                <div class="value">${requestScope.roomDto.area} M<sup>2</sup></div>
                            </div>
                        </div>
                    </div>
                    <div class="room-props-control">
                        <div class="room-price">
                            <fmt:message key="price"/>: ${requestScope.roomDto.price}$/<fmt:message key="night"/>
                        </div>
                    </div>
                </div>
                <div class="room-description-container">
                    <div class="room-name">
                        <h2>${requestScope.roomDto.name}</h2>
                    </div>
                    <p>${requestScope.roomDto.description}</p>
                </div>
                <div class="room-attributes-container">
                    <c:forEach var="attribute" items="${requestScope.roomDto.attributes}">
                        <div class="room-attribute">
                            <p>${attribute}</p>
                        </div>
                    </c:forEach>
                </div>
                <c:if test="${not empty sessionScope.role and sessionScope.role eq 'CUSTOMER'}">
                    <div class="room-control-container">
                        <form action="<c:url value="/controller"/>" method="get">
                            <input type="hidden" name="command" value="book">
                            <input type="hidden" name="roomNumber" value="${requestScope.roomDto.roomNumber}">
                            <label for="date-of-entry">
                                <fmt:message key="from"/>
                                <input id="date-of-entry" class="form-input" name="entryDate" type="date"
                                       max="2024-01-01" required>
                            </label>
                            <label for="date-of-leaving">
                                <fmt:message key="to"/>
                                <input id="date-of-leaving" class="form-input" name="leavingDate" type="date"
                                       max="2024-01-01" required>
                            </label>
                            <button type="submit" class="form-button"><fmt:message key="book"/></button>
                        </form>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
