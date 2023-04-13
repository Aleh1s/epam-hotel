<%@ attribute name="messages" type="java.util.List" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty messages}">
    <c:forEach var="message" items="${messages}">
        <div style="color: red; font-size: 12px">${message}</div>
    </c:forEach>
</c:if>