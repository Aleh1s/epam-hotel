<%@ include file="/WEB-INF/jspf/encoding.jspf"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Create Manager</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="component/header.jsp"/>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<div class="container">
    <div class="main">
        <div class="form-container">
            <form class="custom-form" action="<c:url value="/controller"/>" method="post">
                <input type="hidden" name="command" value="createManager">

                <h1 class="form-header"><fmt:message key="create.manager"/></h1>
                <div class="input-group">
                    <fmt:message var="email" key="email"/>
                    <input class="form-input" id="email" name="email" type="email" placeholder="${email}" required>
                </div>
                <div class="input-group">
                    <fmt:message var="password" key="password"/>
                    <input class="form-input" id="password" name="password" type="password" placeholder="${password}"
                           required>
                </div>
                <div class="input-group">
                    <fmt:message var="firstName" key="first.name"/>
                    <input class="form-input" id="firstName" name="firstName" type="text" placeholder="${firstName}"
                           required>
                </div>
                <div class="input-group">
                    <fmt:message var="lastName" key="last.name"/>
                    <input class="form-input" id="lastName" name="lastName" type="text" placeholder="${lastName}"
                           required>
                </div>
                <div class="input-group">
                    <fmt:message var="phoneNumber" key="phone.number"/>
                    <input class="form-input" id="phoneNumber" name="phoneNumber" type="tel"
                           placeholder="${phoneNumber}" required>
                </div>
                <button class="btn-primary" type="submit"><fmt:message key="create.manager"/></button>
            </form>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
