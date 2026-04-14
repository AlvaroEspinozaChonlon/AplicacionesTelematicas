import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;

import activities.db.DBInteraction;

import java.awt.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import activities.db.DBInteraction;

public class manager extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse res)
    throws IOException, ServletException
    {
        String password = request.getParameter("password_manager"); 

        HttpSession session = request.getSession(); 
        Integer intentosObj = (Integer) session.getAttribute("intentos");

        PrintWriter out = res.getWriter ();

        try {
            boolean correctlogin = password.equals("admin");
    
             if (correctlogin) {
                out.println("Login successful!: gestor");
                
                session.setAttribute("logged_user", "gestor");

                Cookie usercookie = new Cookie("username", "gestor"); 
                usercookie.setPath("/");
                res.addCookie(usercookie);
                res.sendRedirect("list_manager");
             } else{
                res.sendRedirect("manager.jsp");
             }
        } catch (Exception e) {
            out.println("error: " + e.getMessage());
        }
    }
}


