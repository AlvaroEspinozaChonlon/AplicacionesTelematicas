import java.io.*;
import java.util.*;
import java.awt.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import activities.db.*;
import org.apache.commons.text.StringEscapeUtils;
public class edit_activity extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            String idParam = StringEscapeUtils.escapeHtml4(req.getParameter("id"));
            if (idParam == null || idParam.isEmpty()) {
                res.sendRedirect("list_manager.jsp");
                return;
            }

            int activityId = Integer.parseInt(idParam);
            
            DBInteraction db = new DBInteraction();
            
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
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = StringEscapeUtils.escapeHtml4(req.getParameter("name"));
            String description = StringEscapeUtils.escapeHtml4(req.getParameter("description"));
            String initial = StringEscapeUtils.escapeHtml4(req.getParameter("initial"));
            float cost = Float.parseFloat(req.getParameter("cost"));
            String pavname = StringEscapeUtils.escapeHtml4(req.getParameter("pavname"));
            int total = Integer.parseInt(req.getParameter("total"));
            int occupied = Integer.parseInt(req.getParameter("occupied"));
            
            DBInteraction db = new DBInteraction();
            
            db.updateActivity(id, name, description, initial, cost, pavname, total, occupied);
            db.close();
            
            res.sendRedirect("list_manager");
            
        } catch (Exception e) {
            out.println("Error updating activity: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
