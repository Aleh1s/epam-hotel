<%@ attribute name="name" type="java.lang.String" required="true" %>
<%@ attribute name="value" type="java.lang.String" required="true" %>

<div class="element">
    <label for="value">
        ${name}
    </label>
    <div id="value" class="value">
        <p>${value}</p>
    </div>
</div>