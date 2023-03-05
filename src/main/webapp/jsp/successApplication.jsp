<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Success Application</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="component/header.jsp"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="align-center">
                <div class="success-grid-container">
                    <div class="success-image-container">
                        <img src="../img/success_mark.png" alt="Success mark">
                    </div>
                    <div class="congratulation-container">
                        <h1><fmt:message key="success.application.title"/></h1>
                        <p><fmt:message key="success.application.body"/></p>
                    </div>
                    <div class="horizontal-delimiter-with-text">
                        <p><fmt:message key="application.info"/></p>
                        <div></div>
                    </div>
                    <div class="primary-info-container">
                        <table>
                            <tr class="active">
                                <td><fmt:message key="guests"/></td>
                                <td>${sessionScope.guests}</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="room.class"/></td>
                                <td>${sessionScope.clazz}</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="check.in"/></td>
                                <td>
                                    <fmt:formatDate type="date" value="${sessionScope.checkIn}" pattern="dd-MM-yyyy"/>
                                </td>
                            </tr>
                            <tr>
                                <td><fmt:message key="check.out"/></td>
                                <td>
                                    <fmt:formatDate type="date" value="${sessionScope.checkOut}" pattern="dd-MM-yyyy"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <form class="go-home-form" method="get" action="<c:url value="/controller"/>">
                    <input type="hidden" name="command" value="roomList">
                    <input type="hidden" name="default" value="on">
                    <button type="submit" class="btn-primary"><fmt:message key="go.home"/></button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
