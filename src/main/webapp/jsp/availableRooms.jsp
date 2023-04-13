<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Available rooms</title>
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<c:import url="component/header.jsp"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="sort">
                <form action="<c:url value="/controller"/>" method="get">
                    <input type="hidden" name="command" value="getAvailableRooms"/>
                    <select id="price-sort" name="sort" class="form-control w-25 me-3">
                        <c:set var="price" value="${sessionScope.roomCriteria.price}"/>
                        <c:set var="guests" value="${sessionScope.roomCriteria.guests}"/>
                        <c:set var="clazz" value="${sessionScope.roomCriteria.clazz}"/>

                        <option value="price,asc" ${not empty price and price.name() eq 'ASC' ? 'selected' : ''}>
                            <fmt:message key="price"/> <fmt:message key="cheap.expensive"/>
                        </option>
                        <option value="price,desc" ${not empty price and price.name() eq 'DESC' ? 'selected' : ''}>
                            <fmt:message key="price"/> <fmt:message key="expensive.cheap"/>
                        </option>
                        <option value="guests,asc" ${not empty guests and guests.name() eq 'ASC' ? 'selected' : ''}>
                            <fmt:message key="guests"/> <fmt:message key="lower.higher"/>
                        </option>
                        <option value="guests,desc" ${not empty guests and guests.name() eq 'DESC' ? 'selected' : ''}>
                            <fmt:message key="guests"/> <fmt:message key="higher.lower"/>
                        </option>
                        <option value="class,asc" ${not empty clazz and clazz.name() eq 'ASC' ? 'selected' : ''}>
                            <fmt:message key="class"/> <fmt:message key="standard.president"/>
                        </option>
                        <option value="class,desc" ${not empty clazz and clazz.name() eq 'DESC' ? 'selected' : ''}>
                            <fmt:message key="class"/> <fmt:message key="president.standard"/>
                        </option>
                    </select>
                    <button type="submit" class="btn-primary"><fmt:message key="apply"/></button>
                </form>
            </div>
            <fmt:message var="roomsMessage" key="rooms"/>
            <tags:horizontaldelimiter message="${roomsMessage}"/>
            <div class="room-list">
                <c:forEach var="room" items="${requestScope.availableRooms.result()}">
                    <tags:roomcard room="${room}"/>
                </c:forEach>
            </div>
            <tags:pagination pagesNumber="${requestScope.pagesNumber}" currPage="${requestScope.currPage}"
                             command="getAvailableRooms"/>
        </div>
    </div>
</div>
<c:import url="component/footer.jsp"/>
</body>
</html>
