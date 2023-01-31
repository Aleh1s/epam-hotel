<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sasha
  Date: 09.01.2023
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<c:import url="header.jsp"/>
<div class="container">
    <div class="main">
        <div class="form-container">
            <form class="register-form" action="controller?command=signup" method="post">
                <h1 class="form-header">Signup info</h1>
                <div id="error-container" class="error-container">
                    <p id="error-message">${requestScope.get("errorMessage")}</p>
                </div>
                <div class="input-group">
                    <input class="form-input" id="email" name="email" type="email" placeholder="Email">
                </div>
                <div class="input-group">
                    <input class="form-input" id="password" name="password" type="password" placeholder="Password">
                </div>
                <div class="input-group">
                    <input class="form-input" id="firstName" name="firstName" type="text" placeholder="First name">
                </div>
                <div class="input-group">
                    <input class="form-input" id="lastName" name="lastName" type="text" placeholder="Last name">
                </div>
                <div class="input-group">
                    <input class="form-input" id="phoneNumber" name="phoneNumber" type="tel" placeholder="Phone number">
                </div>
                <div class="input-group">
                    <select class="form-input" id="timezoneOffset" name="timezoneOffset">
                        <option value="-12:00">(GMT -12:00) Eniwetok, Kwajalein</option>
                        <option value="-11:00">(GMT -11:00) Midway Island, Samoa</option>
                        <option value="-10:00">(GMT -10:00) Hawaii</option>
                        <option value="-09:50">(GMT -9:30) Taiohae</option>
                        <option value="-09:00">(GMT -9:00) Alaska</option>
                        <option value="-08:00">(GMT -8:00) Pacific Time (US &amp; Canada)</option>
                        <option value="-07:00">(GMT -7:00) Mountain Time (US &amp; Canada)</option>
                        <option value="-06:00">(GMT -6:00) Central Time (US &amp; Canada), Mexico City</option>
                        <option value="-05:00">(GMT -5:00) Eastern Time (US &amp; Canada), Bogota, Lima</option>
                        <option value="-04:50">(GMT -4:30) Caracas</option>
                        <option value="-04:00">(GMT -4:00) Atlantic Time (Canada), Caracas, La Paz</option>
                        <option value="-03:50">(GMT -3:30) Newfoundland</option>
                        <option value="-03:00">(GMT -3:00) Brazil, Buenos Aires, Georgetown</option>
                        <option value="-02:00">(GMT -2:00) Mid-Atlantic</option>
                        <option value="-01:00">(GMT -1:00) Azores, Cape Verde Islands</option>
                        <option value="+00:00" selected="selected">(GMT) Western Europe Time, London, Lisbon,
                            Casablanca
                        </option>
                        <option value="+01:00">(GMT +1:00) Brussels, Copenhagen, Madrid, Paris</option>
                        <option value="+02:00">(GMT +2:00) South Africa</option>
                        <option value="+03:00">(GMT +3:00) Baghdad, Riyadh</option>
                        <option value="+03:50">(GMT +3:30) Tehran</option>
                        <option value="+04:00">(GMT +4:00) Abu Dhabi, Muscat, Baku, Tbilisi</option>
                        <option value="+04:50">(GMT +4:30) Kabul</option>
                        <option value="+05:00">(GMT +5:00) Islamabad, Karachi, Tashkent</option>
                        <option value="+05:50">(GMT +5:30) Bombay, Calcutta, Madras, New Delhi</option>
                        <option value="+05:75">(GMT +5:45) Kathmandu, Pokhara</option>
                        <option value="+06:00">(GMT +6:00) Almaty, Dhaka, Colombo</option>
                        <option value="+06:50">(GMT +6:30) Yangon, Mandalay</option>
                        <option value="+07:00">(GMT +7:00) Bangkok, Hanoi, Jakarta</option>
                        <option value="+08:00">(GMT +8:00) Beijing, Perth, Singapore, Hong Kong</option>
                        <option value="+08:75">(GMT +8:45) Eucla</option>
                        <option value="+09:00">(GMT +9:00) Tokyo, Seoul, Osaka, Sapporo, Yakutsk</option>
                        <option value="+09:50">(GMT +9:30) Adelaide, Darwin</option>
                        <option value="+10:00">(GMT +10:00) Eastern Australia, Guam, Vladivostok</option>
                        <option value="+10:50">(GMT +10:30) Lord Howe Island</option>
                        <option value="+11:00">(GMT +11:00) Magadan, Solomon Islands, New Caledonia</option>
                        <option value="+11:50">(GMT +11:30) Norfolk Island</option>
                        <option value="+12:00">(GMT +12:00) Auckland, Wellington, Fiji, Kamchatka</option>
                        <option value="+12:75">(GMT +12:45) Chatham Islands</option>
                        <option value="+13:00">(GMT +13:00) Apia, Nukualofa</option>
                        <option value="+14:00">(GMT +14:00) Line Islands, Tokelau</option>
                    </select>
                </div>
                <button class="form-button" type="submit">Sign up</button>
            </form>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="script.js"></script>
</body>
</html>
