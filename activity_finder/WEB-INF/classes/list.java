import java.io.*;
import java.util.*;
import java.awt.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import activities.db.*;
import org.apache.commons.text.StringEscapeUtils;
public class list extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("GET Request. No Form Data Posted");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        String type;
	    String text;
	    String order;

		ArrayList data = new ArrayList();
	    
		type=StringEscapeUtils.escapeHtml4(req.getParameter("type"));
	    text=StringEscapeUtils.escapeHtml4(req.getParameter("text1"));
	    order=StringEscapeUtils.escapeHtml4(req.getParameter("order"));
	  try{
		DBInteraction db=new DBInteraction();
		
	    if (type.equals("all_activities")){
	        data=db.listallact(); 
	    }
	    else if (type.equals("all_pavillions")){
		    data=db.listallpav(); 
	    }
		else if (type.equals("free_places")){
			data=db.listactfreeplaces();
		}
		else if (type.equals("cost")){
			float price=Float.parseFloat(text);
			data=db.listactprice(price);
		}
		else if (type.equals("name")){
			data=db.listactname(text);
		}
		else if (type.equals("all_activities")){
			data=db.listactbynameordesc(text);
		}
		else{
			data=db.listactpav(text);
		}

		if (type.equals("all_pavillions")){
		    RequestDispatcher layout=req.getRequestDispatcher("layoutpav.jsp");
		    layout.include(req, res);
			for(int i=0;i<data.size();i++) {
			    Pavillion p = (Pavillion)data.get(i);
			    String name = p.getname();
			    String location = p.getlocation();
		        RequestDispatcher outpav=req.getRequestDispatcher("outpav.jsp?name="+name+"&location="+location);
		        outpav.include(req, res);
			}
	    }
        else {
		    RequestDispatcher layout=req.getRequestDispatcher("layoutact.jsp");
		    layout.include(req, res);
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
				RequestDispatcher outact=req.getRequestDispatcher("outact.jsp?id="+id+"&name="+name+"&description="+description+"&initial="+initial+"&cost="+cost+"&pavname="+pavname+"&total="+total+"&occupied="+occupied);
		        outact.include(req, res);			
	        }
		}
	    RequestDispatcher end=req.getRequestDispatcher("end.jsp");
	    end.include(req, res);
	    db.close();
	  } //end try
	  catch (Exception e){  }
	}//doPost end
}//class end