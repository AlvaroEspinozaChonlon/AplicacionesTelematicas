<%
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
    <form action="login" method=POST>
    <h2>Iniciar sesión</h2>
        <input type="text" name="username" placeholder="Usuario" value="<%= userCookie %>" required><br><br>
        <input type="password" name="password" placeholder="Contraseña" value="<%= passCookie %>" required><br><br>
        <button type="submit">Entrar</button>
    </form>
</body>
</html>
