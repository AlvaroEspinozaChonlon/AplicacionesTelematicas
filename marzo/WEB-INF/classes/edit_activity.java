import java.io.*;
import java.util.*;
import java.awt.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import activities.db.*;

public class edit_activity extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            String idParam = req.getParameter("id");
            
            if (idParam == null || idParam.isEmpty()) {
                res.sendRedirect("list_manager.jsp");
                return;
            }

            int activityId = Integer.parseInt(idParam);
            
            // Create database connection
            DBInteraction db = new DBInteraction();
            
            // Get the activity from database
            ArrayList activities = db.listallact();
            Activity activity = null;
            
            for (int i = 0; i < activities.size(); i++) {
                Activity a = (Activity) activities.get(i);
                if (a.getid() == activityId) {
                    activity = a;
                    break;
                }
            }
            
            db.close();
            
            if (activity == null) {
                res.sendRedirect("list_manager.jsp");
                return;
            }
            
            // Store activity in request and forward to JSP
            req.setAttribute("activity", activity);
            RequestDispatcher dispatcher = req.getRequestDispatcher("edit_activity.jsp");
            dispatcher.forward(req, res);
            
        } catch (NumberFormatException e) {
            res.sendRedirect("list_manager.jsp");
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // Handle the form submission for updating the activity
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String initial = req.getParameter("initial");
            float cost = Float.parseFloat(req.getParameter("cost"));
            String pavname = req.getParameter("pavname");
            int total = Integer.parseInt(req.getParameter("total"));
            int occupied = Integer.parseInt(req.getParameter("occupied"));
            
            DBInteraction db = new DBInteraction();
            
            // Update activity in database
            String updateQuery = "UPDATE ACTIVITIES SET NAME='" + name + "', DESCRIPTION='" + description + 
                                "', START_DATE='" + initial + "', COST=" + cost + ", PAVILLION_NAME='" + pavname + 
                                "', TOTAL_PLACES=" + total + ", OCCUPIED_PLACES=" + occupied + " WHERE ID=" + id;
            
            db.updateActivity(updateQuery);
            db.close();
            
            res.sendRedirect("list_manager");
            
        } catch (Exception e) {
            out.println("Error updating activity: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
