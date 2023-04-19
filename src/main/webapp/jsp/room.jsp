<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Room</title>
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<c:import url="component/header.jsp"/>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="room-grid-container">
                <div class="room-image-container">
                    <img src="http://localhost:8080/controller?command=getRoomImage&roomNumber=${requestScope.roomDto.number}"
                         alt="Room image">
                </div>
                <div class="room-properties-container">
                    <div class="room-property-list">
                        <ul class="list-group w-100">
                            <li class="list-group-item" style="color: #22406c"><strong><fmt:message key="room.number"/>:</strong> ${requestScope.roomDto.number}</li>
                            <li class="list-group-item" style="color: #22406c"><strong><fmt:message key="class"/>:</strong> ${requestScope.roomDto.clazz}</li>
                            <li class="list-group-item" style="color: #22406c"><strong><fmt:message key="persons"/>:</strong> ${requestScope.roomDto.guests}</li>
                            <li class="list-group-item" style="color: #22406c"><strong><fmt:message key="beds"/>:</strong> ${requestScope.roomDto.beds}</li>
                            <li class="list-group-item" style="color: #22406c"><strong><fmt:message key="area"/>:</strong> ${requestScope.roomDto.area} M<sup>2</sup></li>
                        </ul>
                    </div>
                    <div class="room-props-control">
                        <div class="room-price">
                            <c:choose>
                                <c:when test="${requestScope.renderButton}">
                                    $ ${requestScope.roomDto.price}
                                </c:when>
                                <c:otherwise>
                                    $ ${requestScope.roomDto.price}/<fmt:message key="night"/>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <c:if test="${requestScope.renderButton and not empty sessionScope.role}">
                            <form action="<c:url value="/controller"/>" method="post">
                                <input type="hidden" name="command" value="book">
                                <button type="submit" class="form-button"><fmt:message key="book"/></button>
                            </form>
                        </c:if>
                    </div>
                </div>
                <div class="room-description-container">
                    <div class="room-name">
                        <h2>${requestScope.roomDto.title}</h2>
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
            </div>
        </div>
    </div>
</div>
<c:import url="component/footer.jsp"/>
</body>
</html>
