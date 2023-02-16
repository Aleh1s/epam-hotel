<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Request</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="component/header.jsp"/>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="request-container">
                <form class="request-form" action="<c:url value="/controller"/>" method="post">
                    <h1 class="form-header"><fmt:message key="request.info"/></h1>
                    <input type="hidden" name="applicationId" value="${sessionScope.applicationId}">
                    <input type="hidden" name="command" value="makeRequest">
                    <div class="form-center">
                        <div class="input-group">
                            <label for="room-number">
                                <fmt:message key="room.number"/>
                                <input id="room-number" placeholder="<fmt:message key="room.number"/>" type="number"
                                       name="roomNumber"
                                       class="form-input" value="1" min="1" required>
                            </label>
                        </div>
                        <div class="input-group">
                            <label for="date-of-entry">
                                <fmt:message key="entry.date"/>
                                <input id="date-of-entry" class="form-input" name="entryDate" type="date"
                                       max="2024-01-01" required>
                            </label>
                            <label for="date-of-leaving">
                                <fmt:message key="leaving.date"/>
                                <input id="date-of-leaving" class="form-input" name="leavingDate" type="date"
                                       max="2024-01-01" required>
                            </label>
                        </div>
                        <button type="submit" class="request-button"><fmt:message key="send.request"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
