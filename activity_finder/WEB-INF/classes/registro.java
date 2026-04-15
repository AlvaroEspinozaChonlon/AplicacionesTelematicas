import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import activities.db.DBInteraction;

import java.awt.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.apache.commons.text.StringEscapeUtils;

public class registro extends HttpServlet {

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
			String username = StringEscapeUtils.escapeHtml4(request.getParameter("username"));
            String password = StringEscapeUtils.escapeHtml4(request.getParameter("password"));
            String name = StringEscapeUtils.escapeHtml4(request.getParameter("name"));
            String surname = StringEscapeUtils.escapeHtml4(request.getParameter("surname"));
            String address = StringEscapeUtils.escapeHtml4(request.getParameter("address"));
            String phone = StringEscapeUtils.escapeHtml4(request.getParameter("phone"));
            
            Cookie c1 = new Cookie("username", username);
            c1.setPath("/"); 
            res.addCookie(c1);

            Cookie c2 = new Cookie("password", URLEncoder.encode(password, StandardCharsets.UTF_8.toString()));
            c2.setPath("/");
            res.addCookie(c2);

            Cookie c3 = new Cookie("name", URLEncoder.encode(name, StandardCharsets.UTF_8.toString()));
            c3.setPath("/");
            res.addCookie(c3);

            Cookie c4 = new Cookie("surname", URLEncoder.encode(surname, StandardCharsets.UTF_8.toString()));
            c4.setPath("/");
            res.addCookie(c4);

            Cookie c5 = new Cookie("address", URLEncoder.encode(address, StandardCharsets.UTF_8.toString()));
            c5.setPath("/");
            res.addCookie(c5);

            Cookie c6 = new Cookie("phone", URLEncoder.encode(phone, StandardCharsets.UTF_8.toString()));
            c6.setPath("/");
            res.addCookie(c6);

            
            res.sendRedirect("confirm.jsp");
            // try {
            //     DBInteraction db = new DBInteraction();
            //     db.addusr(username, password, name, surname, address, phone);
            //     out.println("User added successfully!: " + username);
            //     db.close();
            // } catch (Exception e) {
            //     out.println("error: " + e.getMessage());
            // }
    }
}
