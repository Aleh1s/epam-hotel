<%@ page import="java.lang.String" %>
<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Room Editor</title>
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
                        <img src="http://localhost:8080/controller?command=getRoomImage&roomNumber=${roomDto.number}" alt="Room image">
                    </div>
                    <tags:fielderror messages="${errors['image']}"/>
                </div>
                <div class="room-properties-editor">
                    <c:set var="index" value="${roomDto.clazz.index}"/>

                    <div id="class" class="form-group w-100">
                        <label for="class" class="form-label fs-6"><fmt:message key="class"/></label>
                        <select name="class" form="editForm" class="form-select" required>
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
                    <div class="form-group w-100">
                        <label for="guests" class="form-label fs-6"><fmt:message key="guests"/></label>
                        <input class="form-control" form="editForm" id="guests" type="text" name="guests"
                        <c:if test="${empty errors['guests']}">
                               value="${roomDto.guests}"
                        </c:if> required>
                        <tags:fielderror messages="${errors['guests']}"/>
                    </div>
                    <div class="form-group w-100">
                        <label for="beds" class="form-label fs-6"><fmt:message key="beds"/></label>
                        <input form="editForm" id="beds" class="form-control" type="text" name="beds"
                        <c:if test="${empty errors['beds']}">
                               value="${roomDto.beds}"
                        </c:if> required>
                        <tags:fielderror messages="${errors['beds']}"/>
                    </div>
                    <div class="form-group w-100">
                        <label for="area" class="form-label fs-6"><fmt:message key="area"/></label>
                        <input form="editForm" class="form-control" id="area" type="text" name="area"
                        <c:if test="${empty errors['area']}">
                               value="${roomDto.area}"
                        </c:if> required>
                        <tags:fielderror messages="${errors['area']}"/>
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
                                <input class="form-control" form="editForm" id="price" type="text" name="price"
                                <c:if test="${empty errors['price']}">
                                       value="${roomDto.price}"
                                </c:if> required>
                                <tags:fielderror messages="${errors['price']}"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="room-description-container">
                    <div style="margin-bottom: 20px" class="form-group">
                        <label for="title" class="form-label fs-6"><fmt:message key="title"/></label>
                        <input class="form-control" form="editForm" id="title" type="text" name="title"
                               value="${roomDto.title}"/>
                        <tags:fielderror messages="${errors['title']}"/>
                    </div>
                    <div class="form-group">
                        <label for="description" class="form-label fs-6"><fmt:message key="description"/></label>
                        <textarea style="min-height: 150px" class="form-control" form="editForm" id="description" name="description" required>${roomDto.description}</textarea>
                        <tags:fielderror messages="${errors['description']}"/>
                    </div>
                </div>
                <div class="attributes-container">
                    <div class="form-group">
                        <label for="attributes" class="form-label fs-6"><fmt:message key="attributes"/></label>
                        <input class="form-control" id="attributes" form="editForm" type="text" name="attributes"
                               value="${String.join(",", roomDto.attributes)}" required>
                        <tags:fielderror messages="${errors['attributes']}"/>
                    </div>
                </div>
                <div class="button-container">
                    <form onsubmit="trimOnSubmit()" class="edit-room-form" method="post" id="editForm" action="<c:url value="/controller"/>"
                          enctype="multipart/form-data">
                        <input type="hidden" name="command" value="updateRoom">
                        <input type="hidden" name="number" value="${requestScope.roomDto.number}">
                        <input type="hidden" name="isUnavailable" value="${requestScope.roomDto.isUnavailable}">
                        <button type="submit" class="btn btn-success w-100"><fmt:message key="save"/></button>
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
