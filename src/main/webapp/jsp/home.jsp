<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<div class="home-image">
    <img src="../img/background_image.jpg" alt="Hotel image">
</div>
<c:import url="component/header.jsp"/>
<div class="container">
    <div class="search-bar-container">
        <div class="home-search-bar">
            <form method="get" action="<c:url value="/controller"/>">
                <input type="hidden" name="command" value="getAvailableRooms">
                <input type="hidden" name="page" value="home"/>

                <div class="bounded-input">
                    <label for="check-in" class="form-label"><fmt:message key="check.in"/></label>
                    <input id="check-in" name="checkIn" type="date"
                           max="2024-01-01" required>
                </div>

                <div class="bounded-input">
                    <label for="check-out" class="form-label"><fmt:message key="check.out"/></label>
                    <input id="check-out" name="checkOut" type="date"
                           max="2024-01-01" required>
                </div>

                <button type="submit" class="btn-primary"><fmt:message key="search"/></button>
            </form>
        </div>
    </div>
</div>
<c:import url="component/footer.jsp"/>
</body>
</html>
