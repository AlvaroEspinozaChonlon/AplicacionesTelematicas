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

    <!-- Fuentes -->
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500&family=DM+Serif+Display&display=swap" rel="stylesheet">

    <style>
        body {
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background: #f4f3f0;
            font-family: 'DM Sans', sans-serif;
        }

        .card {
            background: #fff;
            border-radius: 16px;
            padding: 2.2rem 2rem;
            width: 360px;
            text-align: center;
            box-shadow: 0 2px 20px rgba(0,0,0,0.07);
        }

        h1 {
            font-family: 'DM Serif Display', serif;
            font-weight: 400;
            font-size: 26px;
            color: #1a1a2e;
            margin-bottom: 2rem;
        }

        .button-group {
            display: flex;
            flex-direction: column;
            gap: 12px;
        }

        button {
            width: 100%;
            padding: 12px;
            font-family: 'DM Sans', sans-serif;
            font-size: 14px;
            font-weight: 500;
            border-radius: 8px;
            cursor: pointer;
            border: none;
            transition: all 0.2s ease;
        }

        /* Primario */
        .login {
            background: #1a1a2e;
            color: #fff;
        }

        .login:hover {
            background: #2d2d4e;
        }

        /* Secundario */
        .register {
            background: #fafafa;
            color: #1a1a2e;
            border: 1px solid #e5e5e5;
        }

        .register:hover {
            background: #fff;
            border-color: #7F77DD;
        }

        /* Gestor */
        .gestor {
            background: #7F77DD;
            color: #fff;
        }

        .gestor:hover {
            background: #6b63c7;
        }
    </style>
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