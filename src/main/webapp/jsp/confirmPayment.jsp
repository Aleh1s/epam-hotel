<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/style.css"/>
    <title>Payment info</title>
</head>
<body>
<c:import url="header.jsp"/>

<c:if test="${not empty requestScope.errorMessage}">
    <div class="error-container">
        <p>${requestScope.errorMessage}</p>
    </div>
</c:if>

<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="form-aligner">
                <form class="payment-form" action="<c:url value="/controller"/>" method="post">
                    <input type="hidden" name="command" value="confirmPayment"/>

                    <h1 class="form-header">Payment info</h1>

                    <div class="form-center">
                        <table class="info-table">
                            <tr>
                                <td>Room class:</td>
                                <td>${requestScope.roomDto.roomClass}</td>
                            </tr>
                            <tr>
                                <td>Room price:</td>
                                <td>${requestScope.roomDto.price}$/Night</td>
                            </tr>
                            <tr>
                                <td>Beds number:</td>
                                <td>${requestScope.roomDto.bedsNumber}</td>
                            </tr>
                            <tr>
                                <td>Persons number:</td>
                                <td>${requestScope.roomDto.personsNumber}</td>
                            </tr>
                            <tr>
                                <td>Room area:</td>
                                <td>${requestScope.roomDto.area} M<sup>2</sup></td>
                            </tr>
                            <tr>
                                <td>Entry date:</td>
                                <td><fmt:formatDate type="date" value="${requestScope.reservationDto.entryDate}"
                                                    pattern="MMM dd, yyyy"/></td>
                            </tr>
                            <tr>
                                <td>Leaving date:</td>
                                <td><fmt:formatDate type="date" value="${requestScope.reservationDto.leavingDate}"
                                                    pattern="MMM dd, yyyy"/></td>
                            </tr>
                            <tr>
                                <td>Total amount:</td>
                                <td>${requestScope.reservationDto.totalAmount}$</td>
                            </tr>
                        </table>

                        <button type="submit" class="payment-button">Pay</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
