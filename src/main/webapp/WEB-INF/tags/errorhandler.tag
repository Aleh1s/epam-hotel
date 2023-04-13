<%@ attribute name="message" type="java.lang.String" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty message}">
    <div class="card text-white bg-danger mb-3" style="max-width: 18rem; position: absolute; top: 55px; right: 5px;">
        <div class="card-header">Notification</div>
        <div class="card-body">
            <p class="card-text">${message}</p>
        </div>
    </div>
</c:if>