<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
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
                        <h1>Application Successful!</h1>
                        <p>Your application will be processed by manager! Details of application are included below.</p>
                    </div>
                    <div class="horizontal-delimiter-with-text">
                        <p>Application info</p>
                        <div></div>
                    </div>
                    <div class="primary-info-container">
                        <table>
                            <tr class="active">
                                <td>Guests Number</td>
                                <td>${sessionScope.guestsNumber}</td>
                                <c:remove var="guestsNumber" scope="session"/>
                            </tr>
                            <tr>
                                <td>Room Class</td>
                                <td>${sessionScope.roomClass}</td>
                                <c:remove var="roomClass" scope="session"/>
                            </tr>
                            <tr>
                                <td>Entry Date</td>
                                <td>${sessionScope.entryDate}</td>
                                <c:remove var="entryDate" scope="session"/>
                            </tr>
                            <tr>
                                <td>Leaving Date</td>
                                <td>${sessionScope.leavingDate}</td>
                                <c:remove var="leavingDate" scope="session"/>
                            </tr>
                        </table>
                    </div>
                </div>
                <form class="go-home-form" method="get" action="<c:url value="/controller"/>">
                    <input type="hidden" name="command" value="roomList">
                    <input type="hidden" name="default" value="on">
                    <button type="submit" class="btn-primary">Go Home</button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
