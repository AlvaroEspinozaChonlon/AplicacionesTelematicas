
<%
    String username = new String();
    String password = new String();
    String name = new String();
    String surname = new String();
    String address = new String();
    String phone = new String();

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

    <h2>Registro</h2>
    <form action="registro" method=POST>
        <input type="text" name="name" placeholder="Nombre" required value="<%= name %>"><br><br>
        <input type="text" name="surname" placeholder="Apellido" required value="<%= surname %>"><br><br>
        <input type="text" name="username" placeholder="Usuario" required value="<%= username %>"><br><br>
        <input type="password" name="password" placeholder="Contraseña" required value="<%= password %>"><br><br>
        <input type="text" name="address" placeholder="Dirección" required value="<%= address %>"><br><br>
        <input type="text" name="phone" placeholder="Teléfono" required value="<%= phone %>"><br><br>
        <button type="submit">Registrarse</button>
    </form>
</body>
</html>