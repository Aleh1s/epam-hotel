<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Signup</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<c:import url="component/header.jsp"/>
<main class="container">
    <div class="main">
        <div class="form">
            <h1 class="text-center"><fmt:message key="welcome"/></h1>
            <form action="<c:url value="/controller?command=signup"/>" method="post">

                <c:set var="errors" value="${requestScope.errors}"/>
                <div class="form-group">
                    <fmt:message var="email" key="email"/>
                    <label class="form-label fs-6" for="email">${email}</label>
                    <input class="form-control" id="email" name="email" type="email" placeholder="${email}"
                           required>
                    <tags:fielderror messages="${errors['email']}"/>
                </div>

                <div class="form-group">
                    <fmt:message var="password" key="password"/>
                    <label class="form-label fs-6" for="password">${password}</label>
                    <input class="form-control" id="password" name="password" type="password"
                           placeholder="${password}"
                           required>
                    <tags:fielderror messages="${errors['password']}"/>
                </div>
                <div class="form-group">
                    <fmt:message var="firstName" key="first.name"/>
                    <label class="form-label fs-6" for="firstName">${firstName}</label>
                    <input class="form-control" id="firstName" name="firstName" type="text"
                           placeholder="${firstName}"
                           required>
                    <tags:fielderror messages="${errors['firstName']}"/>
                </div>
                <div class="form-group">
                    <fmt:message var="lastName" key="last.name"/>
                    <label class="form-label fs-6" for="lastName">${lastName}</label>
                    <input class="form-control" id="lastName" name="lastName" type="text" placeholder="${lastName}"
                           required>
                    <tags:fielderror messages="${errors['lastName']}"/>
                </div>
                <div class="form-group">
                    <fmt:message var="phoneNumber" key="phone.number"/>
                    <label class="form-label fs-6" for="phoneNumber">${phoneNumber}</label>
                    <div class="input-group">
                        <span class="input-group-text" id="phone-start-addon">+380</span>
                        <input class="form-control" id="phoneNumber" name="phoneNumber" type="text"
                               placeholder="${phoneNumber}" required aria-describedby="phone-start-addon">
                    </div>
                    <tags:fielderror messages="${errors['phoneNumber']}"/>
                </div>

                <div class="form-group">
                    <a class="link-primary fs-6" href="<c:url value="/jsp/login.jsp"/>">Have already account? Log
                        in!</a>
                </div>

                <div class="form-input">
                    <input class="btn btn-success w-100 fs-6" type="submit" value="<fmt:message key="sign.up"/>"/>
                </div>
            </form>
        </div>
    </div>
</main>
<c:import url="component/footer.jsp"/>
</body>
</html>
