<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Activity</title>
</head>
<body>
    <h1>Register New Activity</h1>
    <hr>
    <form action="add_activity" method="post">
        <table>
            <tr><td>Name:</td><td><input type="text" name="name" required></td></tr>
            <tr><td>Description:</td><td><textarea name="description"></textarea></td></tr>
            <tr><td>Initial Date:</td><td><input type="text" name="initial" placeholder="YYYY-MM-DD"></td></tr>
            <tr><td>Cost:</td><td><input type="number" step="0.01" name="cost"></td></tr>
            <tr><td>Pavillion Name:</td><td><input type="text" name="pavname"></td></tr>
            <tr><td>Total Places:</td><td><input type="number" name="total"></td></tr>
            <tr><td>Occupied Places:</td><td><input type="number" name="occupied" value="0"></td></tr>
            <tr>
                <td colspan="2">
                    <br>
                    <button type="submit" class="btn-submit">Save Activity</button>
                    <a href="list_manager" class="btn-cancel">Go back</a>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>