<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Access denied</title>
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<c:import url="component/header.jsp"/>
<div class="container">
    <div class="d-flex flex-column justify-content-center align-items-center">
        <h1 class="fs-1">403</h1>
        <h1 class="fs-3">Access Denied</h1>
        <p style="color: gray" class="fs-6">You do not have permissions to access this resource</p>
        <form method="get" action="<c:url value="/jsp/home.jsp"/>">
            <button type="submit" class="btn-primary"><fmt:message key="go.home"/></button>
        </form>
    </div>
</div>
<c:import url="component/footer.jsp"/>
</body>
</html>
