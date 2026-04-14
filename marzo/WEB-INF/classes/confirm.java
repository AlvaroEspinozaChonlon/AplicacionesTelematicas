import java.io.*;
import java.util.*;

import activities.db.DBInteraction;

import java.awt.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class confirm extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("GET Request. No Form Data Posted");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse res)
    throws IOException, ServletException
    {
        String username = new String();
        String password = new String();
        String name = new String();
        String surname = new String();
        String address = new String();
        String phone = new String();

        Cookie[] cookies = request.getCookies();
        
        if (cookies != null){
            for (Cookie cookie: cookies){
                if ("username".equals(cookie.getName())){
                    username=cookie.getValue();
                }
                if ("password".equals(cookie.getName())){
                    password=cookie.getValue();
                }
                if ("name".equals(cookie.getName())){
                    name=cookie.getValue();
                }
                if ("surname".equals(cookie.getName())){
                    surname=cookie.getValue();
                }
                if ("address".equals(cookie.getName())){
                    address=cookie.getValue();
                }
                if ("phone".equals(cookie.getName())){
                    phone=cookie.getValue();
                }
            }
            try {
                DBInteraction db = new DBInteraction();
                db.addusr(username, password, name, surname, address, phone);
                db.close();
                
                res.sendRedirect("login.jsp");
            } catch (Exception e) {
                res.getWriter().println("Error al registrar: " + e.getMessage());
            }
        }
    }
}
