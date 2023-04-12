<%@ page import="java.lang.String" %>
<%@ include file="/WEB-INF/jspf/encoding.jspf" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Create Room</title>
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
                    <div class="editor-input">
                        <label for="number">
                            <fmt:message key="room.number"/>
                        </label>
                        <input form="editForm" id="number" type="number" name="number" min="1"
                               value="1" required/>
                    </div>
                    <div id="class" class="editor-input">
                        <label for="class">
                            <fmt:message key="class"/>
                        </label>
                        <select name="class" form="editForm" required>
                            <option value="1" selected>
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
                    </div>
                    <div class="editor-input">
                        <label for="guests">
                            <fmt:message key="guests"/>
                        </label>
                        <input form="editForm" id="guests" type="number" name="guests" min="1"
                               value="1" required/>
                    </div>
                    <div class="editor-input">
                        <label for="beds">
                            <fmt:message key="beds"/>
                        </label>
                        <input form="editForm" id="beds" type="number" name="beds" min="1"
                               value="1" required/>
                    </div>
                    <div class="editor-input">
                        <label for="area">
                            <fmt:message key="area"/>
                        </label>
                        <input form="editForm" id="area" type="number" name="area" min="1"
                               value="10" required/>
                    </div>
                    <div class="editor-input">
                        <label for="price">
                            <fmt:message key="price"/>
                        </label>
                        <input form="editForm" id="price" type="number" name="price" min="1"
                               value="50" required/>
                    </div>
                </div>
                <div class="room-description-container">
                    <div class="room-image">
                        <div class="editor-input">
                            <label for="image">
                                <fmt:message key="image"/>
                            </label>
                            <input form="editForm" id="image" type="file" name="image" accept=".png,.jpg,.jpeg" required/>
                        </div>
                    </div>
                    <div class="room-name">
                        <div class="editor-input">
                            <label for="title">
                                <fmt:message key="title"/>
                            </label>
                            <input form="editForm" id="title" type="text" name="title" required
                                   maxlength="80" placeholder="Max length is 80"/>
                        </div>
                    </div>
                    <textarea form="editForm" id="description" name="description"
                              required maxlength="2000" placeholder="Max length is 2000"></textarea>
                    <div class="editor-input">
                        <label for="attributes">
                            <fmt:message key="attributes"/>
                        </label>
                        <input id="attributes" form="editForm" type="text" name="attributes"
                               placeholder="Attributes should be separated by ','. Example: balcony,fast wifi"
                               maxlength="1000" required>
                    </div>
                </div>
                <div></div>
                <form class="edit-room-form" id="editForm" method="post" action="<c:url value="/controller"/>"
                      enctype="multipart/form-data">
                    <input type="hidden" name="command" value="createRoom">
                    <button type="submit" class="btn-primary"><fmt:message key="create.room"/></button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="component/footer.jsp"/>
</div>
</body>
</html>
