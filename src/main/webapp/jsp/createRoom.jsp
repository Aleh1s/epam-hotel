<%@ page import="java.lang.String" %>
<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Create Room</title>
    <link rel="stylesheet" href="../css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<c:import url="component/header.jsp"/>
<tags:errorhandler message="${requestScope.errorMessage}"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="room-editor-grid-container">
                <c:set var="errors" value="${requestScope.errors}"/>
                <c:set var="roomDto" value="${requestScope.roomDto}"/>
                <div class="image-picker">
                    <div class="room-image-container" id="imagePreview">
                        <img src="../img/upload_area.png" alt="Room image">
                    </div>
                    <tags:fielderror messages="${errors['image']}"/>
                </div>
                <div class="room-properties-editor">
                    <c:set var="errors" value="${requestScope.errors}"/>
                    <div class="form-group w-100">
                        <label for="roomNumber" class="form-label fs-6"><fmt:message key="room.number"/></label>
                        <input class="form-control" form="editForm" id="roomNumber" type="text" name="number" placeholder="<fmt:message key="room.number"/>"
                        <c:if test="${empty errors['number'] and not empty roomDto}">
                               value="${roomDto.number}"
                        </c:if> required>
                        <tags:fielderror messages="${errors['number']}"/>
                    </div>
                    <div id="class" class="form-group w-100">
                        <label for="class" class="form-label fs-6"><fmt:message key="class"/></label>
                        <select name="class" form="editForm" class="form-select" required>
                            <option value="1">
                                <fmt:message key="standard"/>
                            </option>
                            <option value="2">
                                <fmt:message key="superior"/>
                            </option>
                            <option value="3">
                                <fmt:message key="family"/>
                            </option>
                            <option value="4">
                                <fmt:message key="business"/>
                            </option>
                            <option value="5">
                                <fmt:message key="president"/>
                            </option>
                        </select>
                        <tags:fielderror messages="${errors['clazz']}"/>
                    </div>
                    <div class="form-group w-100">
                        <label for="guests" class="form-label fs-6"><fmt:message key="guests"/></label>
                        <input class="form-control" form="editForm" id="guests" type="text" name="guests" placeholder="<fmt:message key="guests"/>"
                        <c:if test="${empty errors['guests'] and not empty roomDto}">
                               value="${roomDto.guests}"
                        </c:if> required>
                        <tags:fielderror messages="${errors['guests']}"/>
                    </div>
                    <div class="form-group w-100">
                        <label for="beds" class="form-label fs-6"><fmt:message key="beds"/></label>
                        <input form="editForm" id="beds" class="form-control" type="text" name="beds" placeholder="<fmt:message key="beds"/>"
                        <c:if test="${empty errors['beds'] and not empty roomDto}">
                            value="${roomDto.beds}"
                        </c:if> required>
                        <tags:fielderror messages="${errors['beds']}"/>
                    </div>
                </div>
                <div class="image-price-container">
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="imagePicker" class="form-label fs-6"><fmt:message key="image"/></label>
                                <input class="form-control" form="editForm" id="imagePicker" type="file" name="image"
                                       accept=".jpg,.jpeg,.png"/>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="price" class="form-label fs-6"><fmt:message key="price"/></label>
                                <input class="form-control" form="editForm" id="price" type="text" name="price" placeholder="<fmt:message key="price"/>"
                                <c:if test="${empty errors['price'] and not empty roomDto}">
                                       value="${roomDto.price}"
                                </c:if> required>
                                <tags:fielderror messages="${errors['price']}"/>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="area" class="form-label fs-6"><fmt:message key="area"/></label>
                                <input form="editForm" class="form-control" id="area" type="text" name="area" placeholder="<fmt:message key="area"/>"
                                <c:if test="${empty errors['area'] and not empty roomDto}">
                                       value="${roomDto.area}"
                                </c:if> required>
                                <tags:fielderror messages="${errors['area']}"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="room-description-container">
                    <div style="margin-bottom: 20px" class="form-group">
                        <label for="title" class="form-label fs-6"><fmt:message key="title"/></label>
                        <input class="form-control" form="editForm" id="title" type="text" name="title" placeholder="<fmt:message key="title"/>"
                        <c:if test="${not empty roomDto}">
                               value="${roomDto.title}"
                        </c:if> required>
                        <tags:fielderror messages="${errors['title']}"/>
                    </div>
                    <div class="form-group">
                        <label for="description" class="form-label fs-6"><fmt:message key="description"/></label>
                        <textarea style="min-height: 150px" class="form-control" form="editForm" id="description" placeholder="<fmt:message key="description"/>" name="description" required><c:if test="${not empty roomDto}">${roomDto.description}</c:if></textarea>
                        <tags:fielderror messages="${errors['description']}"/>
                    </div>
                </div>
                <div class="attributes-container">
                    <div class="form-group">
                        <label for="attributes" class="form-label fs-6"><fmt:message key="attributes"/></label>
                        <input class="form-control" id="attributes" form="editForm" type="text" name="attributes" placeholder="<fmt:message key="attributes"/>"
                        <c:if test="${not empty roomDto}">
                               value="${String.join(",", roomDto.attributes)}"
                        </c:if> required>
                        <tags:fielderror messages="${errors['attributes']}"/>
                    </div>
                </div>
                <div class="button-container">
                    <form onsubmit="trimOnSubmit()" class="edit-room-form" id="editForm" method="post" action="<c:url value="/controller"/>"
                          enctype="multipart/form-data">
                        <input type="hidden" name="command" value="createRoom">
                        <button type="submit" class="btn btn-success w-100"><fmt:message key="create.room"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="component/footer.jsp"/>
<script src="../js/script.js"></script>
</body>
</html>
