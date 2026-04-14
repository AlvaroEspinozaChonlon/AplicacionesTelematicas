import java.io.*;
import java.util.*;

import activities.db.DBInteraction;

import java.awt.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class login extends HttpServlet {

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
        PrintWriter out = res.getWriter ();
			String usuario = request.getParameter("username");
            String contraseña = request.getParameter("password"); 
            // crear sesión
            HttpSession session = request.getSession(); 
            Integer intentosObj = (Integer) session.getAttribute("intentos");

            // 3. Si es null lo inicializamos a 0
            int intentos = (intentosObj == null) ? 0 : intentosObj.intValue();   

            try {
                DBInteraction db = new DBInteraction();
                boolean correctlogin = db.authentication(usuario, contraseña);
        
                if (correctlogin) {
                    out.println("Login successful!: " + usuario);
                    
                    session.setAttribute("logged_user", usuario);
                    session.setAttribute("intentos", 0);

                    Cookie usercookie = new Cookie("username", usuario); 
                    usercookie.setPath("/");
                    res.addCookie(usercookie);
                    
                    res.sendRedirect("buscador.jsp");
                } else {
                    intentos++;
                    session.setAttribute("intentos", intentos);
                    
                    if (intentos >= 3) {
                        // Al tercer fallo, invalidar y enviar al inicio y se resetean los intentos
                        session.invalidate(); 
                        res.sendRedirect("index.html"); 
                    } else {
                        // Volver a mostrar login con mensaje de error
                        res.sendRedirect("login.jsp?error=true");
                    }
                }
                db.close();
            } catch (Exception e) {
                out.println("error: " + e.getMessage());
            }
    }
}
