<%@ attribute name="message" type="java.lang.String" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty message}">
    <div class="error-container">
        <p>${message}</p>
    </div>
</c:if>