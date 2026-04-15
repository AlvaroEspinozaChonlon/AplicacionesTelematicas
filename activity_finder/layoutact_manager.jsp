<%@ page errorPage="errorHandling.jsp" %>
<%
    String loginUsuario = (String) session.getAttribute("logged_user");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <style>
        .header-user {
            text-align: right;
            font-family: Arial, sans-serif;
            font-size: 14px;
            margin-right: 80px;
            margin-bottom: 10px;
            color: #555;
            }
        .logout-link {
            color: #d9534f;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
<title>Sporting Activities Searching Application</title>
</head>
<body>
 <%@ include file="banner.include" %>
<div class="header-user">
    <!-- Hello, <%= loginUsuario %>! <br> -->
    Hello, Manager! <br>
    <a href="logout" class="logout-link">Logout</a>
</div>
<h1> List of Activities that fulfill the selected criterion </h1>
<form action="add_activity.jsp" method="get">
    <input type="submit" value="Add Activity">
</form>
<table><tr><td><b>ID</b></td><td><b>NAME</b></td><td><b>DESCRIPTION</b></td> <td><b>INITIAL DATE</b></td><td><b>COST</b></td><td><b>PAVILLION NAME</b></td><td><b>TOTAL PLACES</b></td><td><b>OCCUPIED PLACES</b></td></tr><p>
