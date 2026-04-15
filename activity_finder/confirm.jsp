<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
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

    String username = new String();
    String password = new String();
    String name = new String();
    String surname = new String();
    String address = new String();
    String phone = new String();

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            String value = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8.toString());
            
            if (cookie.getName().equals("username"))
                username = value;
            else if (cookie.getName().equals("password")) 
                password = value;
            else if (cookie.getName().equals("name")) 
                name = value;
            else if (cookie.getName().equals("surname")) 
                surname = value;
            else if (cookie.getName().equals("address")) 
                address = value;
            else if (cookie.getName().equals("phone")) 
                phone = value;
        }
    }

    boolean datosCompletos = (
        !username.isEmpty() && 
        !password.isEmpty() && 
        !name.isEmpty() && 
        !surname.isEmpty() && 
        !address.isEmpty() && 
        !phone.isEmpty()
    );

    if (!datosCompletos) {
        response.sendRedirect("registro.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Cookies</title>
    
</head>
<body>
    <h1>Confirm Registration</h1>
    <div class="cookies-container">


        <div class="cookie-item"><strong>Username:</strong> <%= username %></div>
        <div class="cookie-item"><strong>Password:</strong> <%= password %></div>
        <div class="cookie-item"><strong>Name:</strong> <%= name %></div>
        <div class="cookie-item"><strong>Surname:</strong> <%= surname %></div>
        <div class="cookie-item"><strong>Address:</strong> <%= address %></div>
        <div class="cookie-item"><strong>Phone:</strong> <%= phone %></div>        <div class="buttons">

    <form method="POST">
        <button type="submit" formaction="confirm">
            Yes (Go to Login)
        </button>

        <button type="submit" formaction="registro.jsp">
            No (Correct Data)
        </button>
    </form>
    </div>
</body>
</html>