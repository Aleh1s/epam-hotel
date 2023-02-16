<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Application</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="component/header.jsp"/>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<div class="container">
    <div class="main">
        <div class="form-container">
            <form class="custom-form" action="<c:url value="/controller?command=application"/>" method="post">
                <h1 class="form-header"><fmt:message key="application.info"/></h1>

                <div class="input-group">
                    <label for="number-of-guests">
                        <fmt:message var="numberOfGuests" key="number.of.guests"/>
                        ${numberOfGuests}
                        <input min="1" max="10" class="form-input" id="number-of-guests"
                               value="1"
                               name="guestsNumber" type="number"
                               placeholder="${numberOfGuests}" required>
                    </label>
                </div>
                <div class="input-group">
                    <label for="roomClass">
                        <fmt:message key="class"/>
                        <select class="form-input" name="roomClass" id="roomClass" required>
                            <option value="1"><fmt:message key="standard"/></option>
                            <option value="2"><fmt:message key="superior"/></option>
                            <option value="3"><fmt:message key="family"/></option>
                            <option value="4"><fmt:message key="business"/></option>
                            <option value="5"><fmt:message key="president"/></option>
                        </select>
                    </label>
                </div>
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
                <button class="btn-primary" type="submit"><fmt:message key="make.an.application"/></button>
            </form>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
