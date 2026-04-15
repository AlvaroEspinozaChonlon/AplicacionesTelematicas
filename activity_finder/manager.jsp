<html>
<body>
<h1>Exclusive access for managers</h1>
<% if (request.getParameter("error") != null) { %>
        <div style="color: red; margin-bottom: 15px;">
            Incorrect password.
        </div>
<% } %>
<form action="manager" method="post">
<input type="password_manager" name="password_manager" placeholder="Password" required>
<input type="submit" value="Submit">
</form>
<a href="index.jsp">Back to home</a>
</body>
</html>