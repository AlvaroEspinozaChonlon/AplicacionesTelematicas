<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    HttpSession sesion = request.getSession(false);

    if (sesion == null || sesion.getAttribute("logged_user") == null) {
        response.sendRedirect("index.jsp"); 
        return; 
    }

    String loginUsuario = (String) sesion.getAttribute("logged_user");

    if ("gestor".equals(loginUsuario)) {
        response.sendRedirect("list_manager");
        return;
    }

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

</head>
<body>
    <div class="header-user">
         Hello, <%= loginUsuario %>! <br>
        <a href="logout" class="logout-link">Logout</a>
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