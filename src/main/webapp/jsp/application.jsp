<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Application</title>
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<c:import url="component/header.jsp"/>
<div class="container">
    <div class="main">
        <div class="form">
            <h1 class="text-center"><fmt:message key="request"/></h1>
            <form onsubmit="trimOnSubmit()" action="<c:url value="/controller?command=application"/>" method="post">

                <c:set var="errors" value="${requestScope.errors}"/>
                <c:set var="credentials" value="${requestScope.credentials}"/>

                <div class="form-group">
                    <fmt:message var="guestsNumber" key="guests"/>
                    <label for="number-of-guests" class="form-label fs-6">${guestsNumber}</label>
                    <input class="form-control" id="number-of-guests" name="guests" type="text" placeholder="${guestsNumber}"
                    <c:if test="${empty errors['guests'] and not empty credentials}">
                           value="${credentials.guests}"
                    </c:if> required>
                    <tags:fielderror messages="${errors['guests']}"/>
                </div>

                <div class="form-group">
                    <label for="roomClass" class="form-label fs-6"><fmt:message key="class"/></label>
                    <c:set var="selected" value="1"/>

                    <c:if test="${empty errors['clazz'] and not empty credentials}">
                        <c:set var="selected" value="${credentials.roomClass.index}"/>
                    </c:if>
                    <select class="form-select" name="clazz" id="roomClass" required>
                        <option value="1" ${selected eq '1' ? 'selected' : ''}><fmt:message key="standard"/></option>
                        <option value="2" ${selected eq '2' ? 'selected' : ''}><fmt:message key="superior"/></option>
                        <option value="3" ${selected eq '3' ? 'selected' : ''}><fmt:message key="family"/></option>
                        <option value="4" ${selected eq '4' ? 'selected' : ''}><fmt:message key="business"/></option>
                        <option value="5" ${selected eq '5' ? 'selected' : ''}><fmt:message key="president"/></option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="date-of-entry" class="form-label fs-6"><fmt:message key="check.in"/></label>
                    <input id="date-of-entry" class="form-control" name="checkIn" type="date" max="2024-01-01"
                    <c:if test="${empty errors['period'] and not empty credentials}">
                           value="${credentials.checkIn}"
                    </c:if> required>
                    <tags:fielderror messages="${errors['period']}"/>
                </div>
                <div class="form-group">
                    <label for="date-of-leaving" class="form-label fs-6"><fmt:message key="check.out"/></label>
                    <input id="date-of-leaving" class="form-control" name="checkOut" type="date" max="2024-01-01"
                    <c:if test="${empty errors['period'] and not empty credentials}">
                           value="${credentials.checkOut}"
                    </c:if> required>
                    <tags:fielderror messages="${errors['period']}"/>
                </div>
                <div class="form-input">
                    <input class="btn btn-success w-100 fs-6" type="submit" value="<fmt:message key="request"/>"/>
                </div>
            </form>
        </div>
    </div>
</div>
<c:import url="component/footer.jsp"/>
</body>
</html>
