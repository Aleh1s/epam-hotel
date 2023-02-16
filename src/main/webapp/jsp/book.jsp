<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Book</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="component/header.jsp"/>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="book-container">
                <form method="post" class="book-form" action="<c:url value="/controller"/>">
                    <h1 class="form-header"><fmt:message key="booking.info"/></h1>
                    <input type="hidden" name="command" value="book"/>
                    <div class="form-center">
                        <div class="input-group">
                            <label for="date-of-entry">
                                <fmt:message key="entry.date"/>
                                <input id="date-of-entry" class="form-input" name="entryDate" type="date"
                                       max="2024-01-01" required>
                            </label>
                        </div>
                        <div class="input-group">
                            <label for="date-of-leaving">
                                <fmt:message key="leaving.date"/>
                                <input id="date-of-leaving" class="form-input" name="leavingDate" type="date"
                                       max="2024-01-01" required>
                            </label>
                        </div>
                        <button type="submit" class="book-button"><fmt:message key="book"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
