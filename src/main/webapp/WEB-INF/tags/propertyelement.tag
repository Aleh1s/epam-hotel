<%@ attribute name="name" type="java.lang.String" required="true" %>
<%@ attribute name="value" type="java.lang.String" required="true" %>

<div class="element">
    <label for="value">
        <strong>${name}</strong>
    </label>
    <div id="value" class="value">
        <p style="margin: 0">${value}</p>
    </div>
</div>