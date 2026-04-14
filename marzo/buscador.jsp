<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    // obtener la sesión actual sin crear una nueva (false)
    HttpSession sesion = request.getSession(false);

    if (sesion == null || sesion.getAttribute("logged_user") == null) {
        // Si no tiene sesión, redirigimos a inicio 
        response.sendRedirect("index.html"); 
        return; // Detenemos la ejecución para que no cargue el resto del buscador
    }

    String loginUsuario = (String) sesion.getAttribute("logged_user");

    String nombre = "";
    String apellido = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if (c.getName().equals("name")) nombre = c.getValue();
            if (c.getName().equals("surname")) apellido = c.getValue();
        }
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Sporting Activities Searching Application: Universidad Carlos III de Madrid</title>
<style>
    /* Diseño profesional minimalista */
    body {
        font-family: 'Segoe UI', Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #ffffff;
        color: #333;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    /* Barra superior de usuario */
    .header-user {
        width: 100%;
        padding: 15px 40px;
        background-color: #f8f9fa;
        text-align: right;
        border-bottom: 1px solid #eee;
        box-sizing: border-box;
    }

    .logout-link {
        color: #d9534f;
        text-decoration: none;
        font-weight: bold;
    }

    /* Contenedor principal */
    .container {
        max-width: 800px;
        margin-top: 50px;
        padding: 20px;
        text-align: center;
    }

    h1 {
        font-size: 24px;
        color: #000;
        margin-bottom: 20px;
    }

    .info-text {
        color: #666;
        margin-bottom: 30px;
        font-size: 14px;
    }

    /* Estilo del formulario */
    .search-box {
        border: 1px solid #ddd;
        border-radius: 12px;
        padding: 30px;
        background-color: #fff;
        box-shadow: 0 4px 6px rgba(0,0,0,0.05);
    }

    select, input[type="text"] {
        width: 100%;
        padding: 12px;
        margin: 10px 0;
        border: 1px solid #ccc;
        border-radius: 6px;
        font-size: 15px;
        box-sizing: border-box;
    }

    .btn-container {
        display: flex;
        gap: 10px;
        margin-top: 20px;
    }

    input[type="submit"], input[type="reset"] {
        flex: 1;
        padding: 12px;
        border: none;
        border-radius: 6px;
        font-weight: bold;
        cursor: pointer;
        text-transform: uppercase;
        transition: 0.3s;
    }

    input[type="submit"] {
        background-color: #000;
        color: #fff;
    }

    input[type="reset"] {
        background-color: #f1f1f1;
        color: #333;
    }

    input[type="submit"]:hover { background-color: #333; }
    input[type="reset"]:hover { background-color: #e2e2e2; }
</style>
</head>
<body>
    <div class="header-user">
        Hola, <%= loginUsuario %>! <br>
        <a href="logout" class="logout-link">Cerrar sesión</a>
    </div>

    <div class="container">
        <h1> Sporting Activities Searching Application: Universidad Carlos III de Madrid </h1>
        <div class="info-text">
            This application allows you visualizing all the sporting activities that are at present in Universidad Carlos III
            de Madrid in its three campus (Getafe, Leganes and Colmenarejo)
        </div>

        <div class="search-box">
            Please select an option <br><br>
            <form action="list" method="POST">
                <select name="type">
                    <option value="all_activities" SELECTED> List all sporting activities 
                    <option value="all_pavillions" SELECTED> List all pavillions
                    <option value="free_places" SELECTED> List activities for which there are currently free places 
                    <option value="cost" SELECTED> List activities for which there are free places costing less than a certain amount 
                    <option value="name" SELECTED> List activities by name
                    <option value="all_activities" SELECTED> List all sporting activities
                </select>
                <p>
                Introduce the name of the activity or the pavillion name depending on your selection:
                <input type="text" name="text1" size=32>
                <p>

                <div class="btn-container">
                    <input type="reset" value="REMOVE">
                    <input type="submit" value="SUBMIT">
                </div>
            </form>
        </div>
    </div>
</body>
</html>