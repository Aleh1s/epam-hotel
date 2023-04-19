<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Create Manager</title>
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
        <div class="form">
            <h1 class="text-center"><fmt:message key="new.manager"/></h1>
            <form onsubmit="trimOnSubmit()" action="<c:url value="/controller"/>" method="post">
                <input type="hidden" name="command" value="createManager">

                <c:set var="errors" value="${requestScope.errors}"/>
                <div class="form-group">
                    <fmt:message var="email" key="email"/>
                    <label for="email" class="form-label fs-6">${email}</label>
                    <input class="form-control" id="email" name="email" type="email" placeholder="${email}"
                           required>
                    <tags:fielderror messages="${errors['email']}"/>
                </div>
                <div class="form-group">
                    <fmt:message var="password" key="password"/>
                    <label for="password" class="form-label fs-6">${password}</label>
                    <input class="form-control" id="password" name="password" type="password"
                           placeholder="${password}"
                           required>
                    <tags:fielderror messages="${errors['password']}"/>
                </div>
                <div class="form-group">
                    <fmt:message var="firstName" key="first.name"/>
                    <label for="firstName" class="form-label fs-6">${firstName}</label>
                    <input class="form-control" id="firstName" name="firstName" type="text"
                           placeholder="${firstName}"
                           required>
                    <tags:fielderror messages="${errors['firstName']}"/>
                </div>
                <div class="form-group">
                    <fmt:message var="lastName" key="last.name"/>
                    <label for="lastName" class="form-label fs-6">${lastName}</label>
                    <input class="form-control" id="lastName" name="lastName" type="text" placeholder="${lastName}"
                           required>
                    <tags:fielderror messages="${errors['lastName']}"/>
                </div>
                <div class="form-group">
                    <fmt:message var="phoneNumber" key="phone.number"/>
                    <label for="phoneNumber" class="form-label fs-6">${phoneNumber}</label>
                    <div style="margin-bottom: 0" class="input-group">
                        <span class="input-group-text" id="phone-start-addon">+38</span>
                        <input class="form-control" id="phoneNumber" name="phoneNumber" type="tel"
                               placeholder="${phoneNumber}" required aria-describedby="phone-start-addon">
                    </div>
                    <tags:fielderror messages="${errors['phoneNumber']}"/>
                </div>
                <div class="form-input">
                    <input class="btn btn-success w-100 fs-6" type="submit"
                           value="<fmt:message key="create.manager"/>"/>
                </div>
            </form>
        </div>
    </div>
</div>
<c:import url="component/footer.jsp"/>
</body>
</html>
