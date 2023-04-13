<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
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
            <h1 class="text-center"><fmt:message key="welcome"/></h1>
            <form action="<c:url value="/controller?command=login"/>" method="post">

                <c:set var="errors" value="${requestScope.errors}"/>
                <div class="form-group">
                    <fmt:message var="email" key="email"/>
                    <label for="email" class="form-label">${email}</label>
                    <input class="form-control" id="email" name="email" type="email" placeholder="${email}" required>
                    <tags:fielderror messages="${errors['email']}"/>
                </div>

                <div class="form-group">
                    <fmt:message var="password" key="password"/>
                    <label for="password" class="form-label">${password}</label>
                    <input class="form-control" id="password" name="password" type="password" placeholder="${password}" required>
                    <tags:fielderror messages="${errors['password']}"/>
                </div>

                <div class="form-group">
                    <a class="link-primary fs-6" href="<c:url value="signup.jsp"/>">Have no account? Sign up!</a>
                </div>

                <div class="form-input">
                    <input class="btn btn-success w-100 fs-6" type="submit" value="<fmt:message key="log.in"/>"/>
                </div>
            </form>
        </div>
    </div>
</div>
<c:import url="component/footer.jsp"/>
</body>
</html>
