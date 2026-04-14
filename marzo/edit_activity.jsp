<%@ page errorPage="errorHandling.jsp" %>
<%@ page import="activities.db.Activity" %>
<%
Activity activity = (Activity) request.getAttribute("activity");
if (activity == null) {
    response.sendRedirect("list_manager");
    return;
}
session.removeAttribute("activity");
    %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Edit Activity - Sporting Activities</title>
</head>
<body>
<div class="form-container">
    <h1>Edit Activity</h1>
    
    <form method="POST" action="edit_activity">
        
        <div class="form-group">
            <label for="id">Activity ID:</label>
            <input type="text" id="id" name="id" value="<%=activity.getid()%>" readonly class="readonly">
            <input type="hidden" name="id" value="<%=activity.getid()%>">
        </div>
        
        <div class="form-group">
            <label for="name">Activity Name:</label>
            <input type="text" id="name" name="name" value="<%=activity.getname()%>" required>
        </div>
        
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" name="description" required><%=activity.getdescription()%></textarea>
        </div>
        
        <div class="form-group">
            <label for="initial">Initial Date:</label>
            <input type="text" id="initial" name="initial" value="<%=activity.getinitial()%>" required>
        </div>
        
        <div class="form-group">
            <label for="cost">Cost:</label>
            <input type="number" id="cost" name="cost" value="<%=activity.getcost()%>" step="0.01" required>
        </div>
        
        <div class="form-group">
            <label for="pavname">Pavillion Name:</label>
            <input type="text" id="pavname" name="pavname" value="<%=activity.getpavname()%>" required>
        </div>
        
        <div class="form-group">
            <label for="total">Total Places:</label>
            <input type="number" id="total" name="total" value="<%=activity.gettotal()%>" required>
        </div>
        
        <div class="form-group">
            <label for="occupied">Occupied Places:</label>
            <input type="number" id="occupied" name="occupied" value="<%=activity.getoccupied()%>" required>
        </div>
        
        <div class="button-group">
            <button type="submit" class="btn-submit">Update Activity</button>
            <a href="list_manager" class="btn-cancel">Go back</a>
        </div>
    </form>
</div>

<%@ include file="copyright.include" %>
</body>
</html>
