import java.io.*;
import java.util.*;
import java.awt.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import activities.db.*;

public class list_manager extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
        String type;
	    String text;
	    String order;
        //Call for a reference for data base access
		ArrayList data = new ArrayList();

	    try{
		    DBInteraction db=new DBInteraction();

            RequestDispatcher layout=req.getRequestDispatcher("layoutact_manager.jsp");
            layout.include(req, res);
            
            data=db.listallact(); 

            for(int i=0;i<data.size();i++) {
                Activity a = (Activity)data.get(i);
                int id = a.getid();
                String name = a.getname();
                String description = a.getdescription();
                String initial = a.getinitial();
                Float cost = a.getcost();
                String pavname = a.getpavname();
                int total = a.gettotal();
                int occupied = a.getoccupied();
                RequestDispatcher outact=req.getRequestDispatcher("outact_manager.jsp?id="+id+"&name="+name+"&description="+description+"&initial="+initial+"&cost="+cost+"&pavname="+pavname+"&total="+total+"&occupied="+occupied);
                outact.include(req, res);
            }
            RequestDispatcher end=req.getRequestDispatcher("end.jsp");
            end.include(req, res);
            db.close();
        } //end try
	  catch (Exception e){  }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
       
	    
	}//doPost end
}//class end