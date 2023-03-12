<%@ page import="java.lang.String" %>
<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Room Editor</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<c:import url="component/header.jsp"/>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="room-grid-container">
                <div class="room-image-container">
                    <img src="https://www.thespruce.com/thmb/2_Q52GK3rayV1wnqm6vyBvgI3Ew=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/put-together-a-perfect-guest-room-1976987-hero-223e3e8f697e4b13b62ad4fe898d492d.jpg"
                         alt="Room image">
                </div>
                <div class="room-properties-editor">
                    <c:set var="index" value="${requestScope.roomDto.clazz().index}"/>
                    <div id="class" class="editor-input">
                        <label for="class">
                            <fmt:message key="class"/>
                        </label>
                        <select name="class" form="editForm" required>
                            <option value="1" ${index eq 1 ? 'selected' : ''}>
                                <fmt:message key="standard"/>
                            </option>
                            <option value="2" ${index eq 2 ? 'selected' : ''}>
                                <fmt:message key="superior"/>
                            </option>
                            <option value="3" ${index eq 3 ? 'selected' : ''}>
                                <fmt:message key="family"/>
                            </option>
                            <option value="4" ${index eq 4 ? 'selected' : ''}>
                                <fmt:message key="business"/>
                            </option>
                            <option value="5" ${index eq 5 ? 'selected' : ''}>
                                <fmt:message key="president"/>
                            </option>
                        </select>
                    </div>
                    <div class="editor-input">
                        <label for="guests">
                            <fmt:message key="guests"/>
                        </label>
                        <input form="editForm" id="guests" type="number" name="guests" min="1"
                               value="${requestScope.roomDto.guests()}"
                               required/>
                    </div>
                    <div class="editor-input">
                        <label for="beds">
                            <fmt:message key="beds"/>
                        </label>
                        <input form="editForm" id="beds" type="number" name="beds" min="1"
                               value="${requestScope.roomDto.beds()}"
                               required/>
                    </div>
                    <div class="editor-input">
                        <label for="area">
                            <fmt:message key="area"/>
                        </label>
                        <input form="editForm" id="area" type="number" name="area" min="1"
                               value="${requestScope.roomDto.area()}"
                               required/>
                    </div>
                    <div class="editor-input">
                        <label for="price">
                            <fmt:message key="price"/>
                        </label>
                        <input form="editForm" id="price" type="number" name="price" min="1"
                               value="${requestScope.roomDto.price()}"
                               required/>
                    </div>
                    <div class="editor-input">
                        <label for="image">
                            <fmt:message key="image"/>
                        </label>
                        <input form="editForm" id="image" type="file" name="image"/>
                    </div>
                </div>
                <div class="room-description-container">
                    <div class="room-name">
                        <div class="editor-input">
                            <label for="title">
                                <fmt:message key="title"/>
                            </label>
                            <input form="editForm" id="title" type="text" name="title"
                                   value="${requestScope.roomDto.title()}"
                                   required/>
                        </div>
                    </div>
                    <textarea form="editForm" id="description" name="description" required> ${requestScope.roomDto.description()}</textarea>
                    <div class="editor-input">
                        <label for="attributes">
                            <fmt:message key="attributes"/>
                        </label>
                        <input id="attributes" form="editForm" type="text" name="attributes"
                               value="${String.join(",", requestScope.roomDto.attributes())}"
                               required>
                    </div>
                </div>
                <div></div>
                <form class="edit-room-form" id="editForm" action="<c:url value="/controller"/>">
                    <input type="hidden" name="command" value="updateRoom">
                    <input type="hidden" name="number" value="${requestScope.roomDto.number()}">
                    <input type="hidden" name="isUnavailable" value="${requestScope.roomDto.isUnavailable()}">
                    <button type="submit" class="btn-primary"><fmt:message key="save"/></button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
