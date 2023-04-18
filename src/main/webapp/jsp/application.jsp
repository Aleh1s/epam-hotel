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
            <form action="<c:url value="/controller?command=application"/>" method="post">

                <c:set var="errors" value="${requestScope.errors}"/>
                <div class="form-group">
                    <fmt:message var="guestsNumber" key="guests"/>
                    <label for="number-of-guests" class="form-label fs-6">${guestsNumber}</label>
                    <input min="1" max="10" class="form-control" id="number-of-guests"
                           value="1"
                           name="guests" type="number"
                           placeholder="${guestsNumber}" required>
                    <tags:fielderror messages="${errors['guests']}"/>
                </div>

                <div class="form-group">
                    <label for="roomClass" class="form-label fs-6"><fmt:message key="class"/></label>
                    <select class="form-select" name="clazz" id="roomClass" required>
                        <option value="1"><fmt:message key="standard"/></option>
                        <option value="2"><fmt:message key="superior"/></option>
                        <option value="3"><fmt:message key="family"/></option>
                        <option value="4"><fmt:message key="business"/></option>
                        <option value="5"><fmt:message key="president"/></option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="date-of-entry" class="form-label fs-6"><fmt:message key="check.in"/></label>
                    <input id="date-of-entry" class="form-control" name="checkIn" type="date"
                           max="2024-01-01" required>
                    <tags:fielderror messages="${errors['period']}"/>
                    <tags:fielderror messages="${errors['checkIn']}"/>
                </div>
                <div class="form-group">
                    <label for="date-of-leaving" class="form-label fs-6"><fmt:message key="check.out"/></label>
                    <input id="date-of-leaving" class="form-control" name="checkOut" type="date"
                           max="2024-01-01" required>
                    <tags:fielderror messages="${errors['period']}"/>
                    <tags:fielderror messages="${errors['checkOut']}"/>

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
