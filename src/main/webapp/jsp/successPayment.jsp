<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<html>
<head>
    <title>Success Payment</title>
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
                        <h1>Payment Successful!</h1>
                        <p>Your payment has been processed! Details of transaction are included below.</p>
                    </div>
                    <div class="horizontal-delimiter-with-text">
                        <p>Transaction info</p>
                        <div></div>
                    </div>
                    <div class="transaction-info-container">
                        <table>
                            <tr class="active">
                                <td>Total Amount</td>
                                <td>$ ${sessionScope.paymentTotalAmount}</td>
                                <c:remove var="paymentTotalAmount" scope="session"/>
                            </tr>
                            <tr>
                                <td>Payment Type</td>
                                <td>Net Banking</td>
                            </tr>
                            <tr>
                                <td>Payment Time</td>
                                <td>${sessionScope.paymentPayedAt}</td>
                                <c:remove var="paymentPayedAt" scope="session"/>
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
