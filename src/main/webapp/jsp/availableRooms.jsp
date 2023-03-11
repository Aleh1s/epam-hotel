<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Available rooms</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="component/header.jsp"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <fmt:message var="sortingMessage" key="sorting"/>
            <tags:horizontaldelimiter message="${sortingMessage}"/>
            <div class="sort">
                <form action="<c:url value="/controller"/>" method="get">
                    <h2><fmt:message key="sorting"/></h2>
                    <input type="hidden" name="command" value="getAvailableRooms"/>
                    <select id="price-sort" name="sort" class="select-primary">
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
                    <button type="submit" class="btn-primary"><fmt:message key="search"/></button>
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
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
