<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session != null && session.getAttribute("logged_user") != null) {
        response.sendRedirect("buscador.jsp");
        return; 
    }

    String userCookie = "";
    String passCookie = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if (c.getName().equals("username")) userCookie = c.getValue();
            if (c.getName().equals("password")) passCookie = c.getValue();
        }
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body style="text-align:center; font-family:Arial; margin-top:50px;">
    <%
    Integer intentosObj = (Integer) session.getAttribute("intentos");
    int intentosRealizados = (intentosObj == null) ? 0 : intentosObj.intValue();
    int intentosRestantes = 3 - intentosRealizados;
    %>
    <form action="login" method=POST>
        <h2>Log in</h2>
        <% if (request.getParameter("error") != null) { %>
            <div style="color: red; margin-bottom: 15px;">
                Invalid username or password.<br>
                <strong>Remaining attempts: <%= intentosRestantes %></strong>
            </div>
        <% } %>

        <input type="text" name="username" placeholder="Username" value="<%= userCookie %>" required><br><br>
        <input type="password" name="password" placeholder="Password" value="<%= passCookie %>" required><br><br>
        <button type="submit">Log in</button>
    </form>
    <div class="nav-buttons">
        <a href="registro.jsp" class="btn-secondary">Create account</a>
        <a href="index.jsp" class="btn-secondary">Back to home</a>
    </div>
</body>
</html>
