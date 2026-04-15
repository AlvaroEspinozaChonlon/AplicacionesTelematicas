<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 1. Verificación de sesión: Si ya está logueado, fuera de aquí
    if (session != null && session.getAttribute("logged_user") != null) {
        response.sendRedirect("buscador.jsp");
        return; 
    }

    // 2. Lógica de Cookies para recordar el usuario
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
    <style>
        .nav-buttons {
            margin-top: 20px;
            display: flex;
            justify-content: center;
            gap: 10px;
        }
        .btn-secondary {
            padding: 8px 16px;
            font-size: 13px;
            cursor: pointer;
            background-color: #f0f0f0;
            border: 1px solid #ccc;
            border-radius: 4px;
            text-decoration: none;
            color: #333;
            transition: 0.2s;
        }
        .btn-secondary:hover {
            background-color: #e2e2e2;
        }
        button[type="submit"] {
            padding: 10px 20px;
            background-color: #1a1a2e;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }
    </style>
</head>
<body style="text-align:center; font-family:Arial; margin-top:50px;">
    <form action="login" method=POST>
    <h2>Log in</h2>
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
