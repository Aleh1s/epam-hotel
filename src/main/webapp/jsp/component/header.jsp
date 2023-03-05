<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<header class="header">
    <div class="nav">
        <div class="nav-left">
            <div class="i18n">
                <form action="<c:url value="/controller"/>" method="post" class="i18n-ua">
                    <input type="hidden" name="command" value="i18n">
                    <input type="hidden" name="lang" value="ua">
                    <button class="i18n-ua-button" type="submit"></button>
                </form>
                <span class="vertical-delimiter"></span>
                <form action="<c:url value="/controller"/>" method="post" class="i18n-en">
                    <input type="hidden" name="command" value="i18n">
                    <input type="hidden" name="lang" value="en">
                    <button class="i18n-en-button" type="submit"></button>
                </form>
            </div>
        </div>
        <ul class="nav-center">
            <li class="nav-item"><a href="<c:url value="/jsp/home.jsp"/>"><fmt:message
                    key="home"/></a></li>
            <c:if test="${not empty sessionScope.role and sessionScope.role eq 'CUSTOMER'}">
                <li class="nav-item"><a href="<c:url value="/jsp/application.jsp"/>"><fmt:message
                        key="request.room"/></a></li>
            </c:if>
            <c:if test="${not empty sessionScope.role and sessionScope.role eq 'MANAGER'}">
                <li class="nav-item"><a
                        href="<c:url value="/controller?command=reservationList"/>"><fmt:message
                        key="reservations"/></a></li>
                <li class="nav-item"><a href="<c:url value="/controller?command=applicationList"/>"><fmt:message
                        key="applications"/></a></li>
            </c:if>
        </ul>
        <ul class="nav-right">
            <li class="nav-item"><a href="<c:url value="/jsp/login.jsp"/>"><fmt:message key="log.in"/></a></li>
            <li class="nav-item"><a href="<c:url value="/jsp/signup.jsp"/>"><fmt:message key="sign.up"/></a></li>
            <c:if test="${not empty sessionScope.id}">
                <li class="nav-item"><a href="<c:url value="/controller?command=profile"/>"><fmt:message
                        key="profile"/></a>
                </li>
            </c:if>
        </ul>
    </div>
</header>