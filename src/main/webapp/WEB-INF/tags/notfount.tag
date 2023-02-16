<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<div class="not-found-container">
    <img src="../img/no_results.webp" alt="No Results">
    <h1><fmt:message key="no.results.found.header"/></h1>
    <p><fmt:message key="no.results.found.body"/></p>
</div>