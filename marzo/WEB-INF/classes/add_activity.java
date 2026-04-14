import java.io.*;
import java.util.*;
import java.awt.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import activities.db.*;

public class add_activity extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("add_activity.jsp");
        dispatcher.forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
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
            db.updateActivity(id, name, description, initial, cost, pavname, total, occupied);
            db.close();
            
            res.sendRedirect("list_manager");
            
        } catch (Exception e) {
            out.println("Error adding activity: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
