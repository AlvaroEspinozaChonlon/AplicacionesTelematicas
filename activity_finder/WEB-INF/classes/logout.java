import java.io.*;
import java.util.*;
import java.awt.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class logout extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); 
        }
        
        // Para borrar una cookie, hay que crear una igual pero con tiempo 0
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                c.setMaxAge(0); // Le decimos al navegador que expire YA 
                c.setPath("/"); // Importante que sea el mismo path que al crearla
                response.addCookie(c);
            }
        }

        response.sendRedirect("index.jsp");
    }
}