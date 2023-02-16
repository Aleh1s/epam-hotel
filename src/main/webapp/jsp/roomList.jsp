<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Room list</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="horizontal-delimiter-with-text">
                <p><fmt:message key="sorting"/></p>
                <div></div>
            </div>
            <div class="sort-control">
                <h2 class="form-header"><fmt:message key="sorting"/></h2>
                <form action="<c:url value="/controller"/>" method="get" class="room-sort-form">
                    <input type="hidden" name="command" value="roomList"/>
                    <div>
                        <label for="price-sort">
                            <fmt:message key="by.price"/>
                        </label>
                        <select id="price-sort" name="sort" class="select-primary">
                            <option value="" disabled ${empty sessionScope.roomListSortParamMap.price ? 'selected' : ''}>
                                <fmt:message key="by.price"/>
                            </option>
                            <option value="price,asc" ${sessionScope.roomListSortParamMap.price eq 'asc' ? 'selected' : ''}>
                                <fmt:message key="lower.higher"/>
                            </option>
                            <option value="price,desc" ${sessionScope.roomListSortParamMap.price eq 'desc' ? 'selected' : ''}>
                                <fmt:message key="higher.lower"/>
                            </option>
                        </select>
                    </div>
                    <div>
                        <label for="guests-sort">
                            <fmt:message key="by.persons.number"/>
                        </label>
                        <select id="guests-sort" name="sort" class="select-primary">
                            <option value="" disabled ${empty sessionScope.roomListSortParamMap.guests ? 'selected' : ''}>
                                <fmt:message key="by.persons.number"/>
                            </option>
                            <option value="guests,asc" ${sessionScope.roomListSortParamMap.guests eq 'asc' ? 'selected' : ''}>
                                <fmt:message key="lower.higher"/>
                            </option>
                            <option value="guests,desc" ${sessionScope.roomListSortParamMap.guests eq 'desc' ? 'selected' : ''}>
                                <fmt:message key="higher.lower"/>
                            </option>
                        </select>
                    </div>
                    <div>
                        <label for="class-sort">
                            <fmt:message key="by.class"/>
                        </label>
                        <select id="class-sort" name="sort" class="select-primary">
                            <option value="" disabled ${empty sessionScope.roomListSortParamMap['class'] ? 'selected' : ''}>
                                <fmt:message key="by.class"/>
                            </option>
                            <option value="class,asc" ${sessionScope.roomListSortParamMap['class'] eq 'asc' ? 'selected' : ''}>
                                <fmt:message key="standard.president"/>
                            </option>
                            <option value="class,desc" ${sessionScope.roomListSortParamMap['class'] eq 'desc' ? 'selected' : ''}>
                                <fmt:message key="president.standard"/>
                            </option>
                        </select>
                    </div>
                    <div>
                        <label for="status-sort">
                            <fmt:message key="by.status"/>
                        </label>
                        <select id="status-sort" name="sort" class="select-primary">
                            <option value="" disabled ${empty sessionScope.roomListSortParamMap.status ? 'selected' : ''}>
                                <fmt:message key="by.status"/>
                            </option>
                            <option value="status,asc" ${sessionScope.roomListSortParamMap.status eq 'asc' ? 'selected' : ''}>
                                <fmt:message key="free.unavailable"/>
                            </option>
                            <option value="status,desc" ${sessionScope.roomListSortParamMap.status eq 'desc' ? 'selected' : ''}>
                                <fmt:message key="unavailable.free"/>
                            </option>
                        </select>
                    </div>
                    <button type="submit" class="room-sort-btn"><fmt:message key="search"/></button>
                </form>
            </div>
            <div class="horizontal-delimiter-with-text">
                <p><fmt:message key="rooms"/></p>
                <div></div>
            </div>
            <div class="room-list">
                <c:forEach var="room" items="${requestScope.roomPage.result}">
                    <div class="room-card">
                        <div class="room-card-image">
                            <img src="https://www.thespruce.com/thmb/2_Q52GK3rayV1wnqm6vyBvgI3Ew=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/put-together-a-perfect-guest-room-1976987-hero-223e3e8f697e4b13b62ad4fe898d492d.jpg"
                                 alt="Room image">
                        </div>
                        <div class="room-card-content">
                            <ul class="key-value-list">
                                <li>
                                    <div class="key"><fmt:message key="status"/>:</div>
                                    <div class="value">${room.roomStatus}</div>
                                </li>
                                <li>
                                    <div class="key"><fmt:message key="class"/>:</div>
                                    <div class="value">${room.roomClass}</div>
                                </li>
                                <li>
                                    <div class="key"><fmt:message key="persons.number"/>:</div>
                                    <div class="value">${room.personsNumber}</div>
                                </li>
                                <li>
                                    <div class="key"><fmt:message key="beds.number"/>:</div>
                                    <div class="value">${room.bedsNumber}</div>
                                </li>
                                <li>
                                    <div class="key"><fmt:message key="area"/>:</div>
                                    <div class="value">${room.area} M<sup>2</sup></div>
                                </li>
                            </ul>
                        </div>
                        <div class="room-card-footer">
                            <p class="room-price-label">${room.price}$/<fmt:message
                                    key="night"/></p>
                            <a href="<c:url value="/controller?command=viewRoom&roomNumber=${room.roomNumber}"/>"><fmt:message
                                    key="view"/></a>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <tags:pagination pagesNumber="${requestScope.pagesNumber}" currPage="${requestScope.currPage}" command="roomList"/>
<%--            <div class="pagination">--%>
<%--                <c:forEach var="i" begin="1" end="${requestScope.pagesNumber}">--%>
<%--                    <c:choose>--%>
<%--                        <c:when test="${requestScope.currPage eq i}">--%>
<%--                            <div class="pagination-elem active">--%>
<%--                                <a href="#">${i}</a>--%>
<%--                            </div>--%>
<%--                        </c:when>--%>
<%--                        <c:otherwise>--%>
<%--                            <div class="pagination-elem">--%>
<%--                                <a href="<c:url value="/controller?command=roomList&pageNumber=${i}&${requestScope.paramString}"/>">${i}</a>--%>
<%--                            </div>--%>
<%--                        </c:otherwise>--%>
<%--                    </c:choose>--%>
<%--                </c:forEach>--%>
<%--            </div>--%>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>