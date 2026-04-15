
<%
    String username = new String();
    String password = new String();
    String name = new String();
    String surname = new String();
    String address = new String();
    String phone = new String();


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

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals("username")) {
                username = cookie.getValue();
            }
            else if (cookie.getName().equals("password")) {
                password = cookie.getValue();
            }
            else if (cookie.getName().equals("name")) {
                name = cookie.getValue();
            }
            else if (cookie.getName().equals("surname")) {
                surname = cookie.getValue();
            }
            else if (cookie.getName().equals("address")) {
                address = cookie.getValue();
            }
            else if (cookie.getName().equals("phone")) {
                phone = cookie.getValue();
            }
        }
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro</title>
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
<body style="text-align:center; font-family:Arial; margin-top:30px;">

    <h2>Register</h2>
    <form action="registro" method=POST>
        <input type="text" name="name" placeholder="Name" required value="<%= name %>"><br><br>
        <input type="text" name="surname" placeholder="Surname" required value="<%= surname %>"><br><br>
        <input type="text" name="username" placeholder="Username" required value="<%= username %>"><br><br>
        <input type="password" name="password" placeholder="Password" required value="<%= password %>"><br><br>
        <input type="text" name="address" placeholder="Address" required value="<%= address %>"><br><br>
        <input type="text" name="phone" placeholder="Phone" required value="<%= phone %>"><br><br>
        <button type="submit">Submit</button>
    </form>
    <div class="nav-buttons">
        <a href="login.jsp" class="btn-secondary">I already have an account</a>
        <a href="index.jsp" class="btn-secondary">Back to home</a>
    </div>
</body>
</html>