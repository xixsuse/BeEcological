package logic.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.Controller.UnloadController;
import logic.Controller.WasteUnloadedController;
import logic.bean.CenterBean;
import logic.bean.CenterOwnerBean;
import logic.bean.UnloadBean;
import logic.bean.WasteUnloadedBean;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DeleteUnloadServlet")
public class DeleteUnloadServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		CenterOwnerBean ownerBean = new CenterOwnerBean();
    	CenterBean centerBean = new CenterBean();
    	WasteUnloadedBean wasteBean = new WasteUnloadedBean();
    	
        ownerBean.setUsername(request.getParameter("username"));
        ownerBean.setEmailAddress(request.getParameter("mail"));
        ownerBean.setPhoneNumber(request.getParameter("ownerphone"));
        centerBean.setName(request.getParameter("centername"));
        centerBean.setAddress(request.getParameter("address"));
        centerBean.setCenterPhone(request.getParameter("centerphone"));
        
        wasteBean.setUser(request.getParameter("userToDelete1"));
    	wasteBean.setCenter(request.getParameter("centername"));
    	wasteBean.setDate(request.getParameter("date1"));
    	wasteBean.setTime(request.getParameter("time1"));
    	wasteBean.setWaste(request.getParameter("waste"));
    	wasteBean.setWasteQuantity(Integer.parseInt(request.getParameter("quantity")));
        
		//cancello lo scarico di un rifiuto e tolgo ecoPoints con trigger
		WasteUnloadedController controller = new WasteUnloadedController();
		controller.DeleteWasteForAnUnload(wasteBean);
		
		HttpSession session = request.getSession(true);
		
        session.setAttribute("loggedOwner", ownerBean);
        session.setAttribute("centerInfo", centerBean);
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Unload waste deleted successfully.');");
        out.println("</script>");
		
		int count = controller.NumberOfWasteForAnUnload(wasteBean);
		if(count==0) {
			//non ho piu niente registrato per quello scarico, lo elimino
	        UnloadBean unload = new UnloadBean();
			unload.setUser(wasteBean.getUser());
			unload.setCenter(wasteBean.getCenter());
			unload.setDate(wasteBean.getDate());
			unload.setTime(wasteBean.getTime());
			UnloadController controller1 = new UnloadController();
	        controller1.DeleteAnUnload(unload);
	        session.setAttribute("loggedOwner", ownerBean);
	        session.setAttribute("centerInfo", centerBean);
	        out.println("<script type=\"text/javascript\">");
	        out.println("alert('Unload deleted successfully. No one waste already exists for this unload.');");
	        out.println("</script>");
		}
        out.println("<script type=\"text/javascript\">");
        out.println("location='homeOwner.jsp';");
        out.println("</script>");
	}

}
