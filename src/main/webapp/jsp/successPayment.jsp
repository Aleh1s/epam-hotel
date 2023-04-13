<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Success Payment</title>
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                        <h1><fmt:message key="success.payment.title"/></h1>
                        <p><fmt:message key="success.payment.body"/></p>
                    </div>
                    <div class="horizontal-delimiter-with-text">
                        <p><fmt:message key="transaction.info"/></p>
                        <div></div>
                    </div>
                    <div class="primary-info-container">
                        <table>
                            <tr class="active">
                                <td><fmt:message key="total.amount"/></td>
                                <td>$ ${sessionScope.paymentTotalAmount}</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="payment.type"/></td>
                                <td>Net Banking</td>
                            </tr>
                            <tr>
                                <td><fmt:message key="payment.time"/></td>
                                <td><fmt:formatDate type="both" value="${sessionScope.paymentPayedAt}"
                                                    pattern="dd-MM-yyyy HH:mm:ss"/></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <form class="go-home-form" method="get" action="<c:url value="/controller"/>">
                    <input type="hidden" name="command" value="myBookings">
                    <button type="submit" class="btn-primary"><fmt:message key="ok"/></button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
