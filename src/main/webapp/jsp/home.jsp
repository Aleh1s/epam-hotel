<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="component/header.jsp"/>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<div class="home-image">
    <img src="https://media.timeout.com/images/103647022/image.jpg" alt="Hotel image">
</div>

<div class="container">
    <div class="search-bar-container">
        <div class="home-search-bar">
            <form method="get" action="<c:url value="/controller"/>">
                <input type="hidden" name="command" value="getAvailableRooms">
                <div class="bounded-input">
                    <label for="check-in">
                        <fmt:message key="check.in"/>
                    </label>
                    <input id="check-in" name="checkIn" type="date"
                           max="2024-01-01" required>
                </div>
                <div class="bounded-input">
                    <label for="check-out">
                        <fmt:message key="check.out"/>
                    </label>
                    <input id="check-out" name="checkOut" type="date"
                           max="2024-01-01" required>
                </div>
                <div class="bounded-input">
                    <label for="guests">
                        <fmt:message key="guests"/>
                    </label>
                    <input id="guests" name="guests" type="number" min="1" max="10" required
                           placeholder="Guests" value="1">
                </div>
                <button type="submit" class="btn-primary"><fmt:message key="search"/></button>
            </form>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
