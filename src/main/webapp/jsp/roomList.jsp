<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Room List</title>
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
            <fmt:message var="roomsMessage" key="rooms"/>
            <tags:horizontaldelimiter message="${roomsMessage}"/>

            <c:forEach var="room" items="${requestScope.roomDtoPage.result()}">
                <tags:roomblock room="${room}"/>
            </c:forEach>

            <tags:pagination pagesNumber="${requestScope.pagesNumber}" currPage="${requestScope.currPage}"
                             command="getRooms"/>
        </div>
    </div>
</div>
<c:import url="component/footer.jsp"/>
</body>
</html>
