<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session != null && session.getAttribute("logged_user") != null) {

        String user = (String) session.getAttribute("logged_user");

        if ("gestor".equals(user)) {
            response.sendRedirect("list_manager");
            return;
        } else {
            response.sendRedirect("buscador.jsp");
            return;
        }
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido</title>

    
</head>

<body>

    <div class="card">
        <h1>Welcome</h1>

        <div class="button-group">
            <button class="login" onclick="location.href='login.jsp'">
                Log in
            </button>

            <button class="register" onclick="location.href='registro.jsp'">
                Register
            </button>

            <button class="gestor" onclick="location.href='manager.jsp'">
                Manager Access
            </button>
        </div>
    </div>

</body>
</html>