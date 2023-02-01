<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Room list</title>
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
    <div class="main">
        <div class="main-container">
            <div class="group">
                <div class="sort-control">
                    <h2 class="form-header">Sorting</h2>
                    <form class="sort-control-form" action="controller?command=roomList">
                        <div class="sort-select-group">
                            <label for="price-select">
                                Price
                                <select id="price-select" class="form-input" name="price">
                                    <option>From lower to higher price</option>
                                    <option>From higher to lower price</option>
                                </select>
                            </label>
                            <label for="number-of-persons-select">
                                Number of persons
                                <select id="number-of-persons-select" class="form-input" name="numberOfPersons">
                                    <option>From lower to higher</option>
                                    <option>From higher to lower</option>
                                </select>
                            </label>
                        </div>
                        <div class="sort-select-group">
                            <label for="class-select">
                                Class
                                <select id="class-select" class="form-input" name="class">
                                    <option>Standard</option>
                                    <option>Superior</option>
                                    <option>Family Room</option>
                                    <option>Business</option>
                                    <option>President</option>
                                </select>
                            </label>
                            <label for="status-select">
                                Status
                                <select id="status-select" class="form-input" name="status">
                                    <option>Free</option>
                                    <option>Booked</option>
                                    <option>Busy</option>
                                    <option>Unavailable</option>
                                </select>
                            </label>
                        </div>
                        <button type="submit" class="form-button">sort</button>
                    </form>
                </div>
            </div>
            <div class="room-list">
                <div class="room-card">
                    <div class="room-card-image">
                        <img src="https://www.thespruce.com/thmb/2_Q52GK3rayV1wnqm6vyBvgI3Ew=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/put-together-a-perfect-guest-room-1976987-hero-223e3e8f697e4b13b62ad4fe898d492d.jpg"
                             alt="">
                    </div>
                    <div class="room-card-content">
                        <ul class="key-value-list">
                            <li>
                                <div class="key">Status:</div>
                                <div class="value">free</div>
                            </li>
                            <li>
                                <div class="key">Class:</div>
                                <div class="value">standard</div>
                            </li>
                            <li>
                                <div class="key">Persons number:</div>
                                <div class="value">2</div>
                            </li>
                        </ul>
                    </div>
                    <div class="room-card-footer">
                        <p class="room-price-label">100$ per night</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>