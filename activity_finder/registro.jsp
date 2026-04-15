
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