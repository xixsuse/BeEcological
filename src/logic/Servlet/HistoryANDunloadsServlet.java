package logic.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.Bean.BookingBean;
import logic.Bean.CenterBean;
import logic.Bean.CenterOwnerBean;
import logic.Bean.WasteUnloadedBean;
import logic.Controller.BookingController;
import logic.Controller.WasteUnloadedController;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/HistoryANDunloadsServlet")
public class HistoryANDunloadsServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	CenterOwnerBean ownerBean = new CenterOwnerBean();
    	CenterBean centerBean = new CenterBean();
        BookingBean bookingBean = new BookingBean();
        WasteUnloadedBean wasteBean = new WasteUnloadedBean();
        ownerBean.setUsername(request.getParameter("username"));
        ownerBean.setEmailAddress(request.getParameter("mail"));
        ownerBean.setPhoneNumber(request.getParameter("ownerphone"));
        centerBean.setName(request.getParameter("centername"));
        centerBean.setAddress(request.getParameter("address"));
        centerBean.setCenterPhone(request.getParameter("centerphone"));
    	bookingBean.setCenter(request.getParameter("centername"));
    	wasteBean.setCenter(request.getParameter("centername"));
    	bookingBean.setStatus("A");
        BookingController controller = new BookingController();
        ArrayList<BookingBean> bookAccept= controller.BookingListByCenter(bookingBean);
        WasteUnloadedController controller1 = new WasteUnloadedController();
        ArrayList<WasteUnloadedBean> unloadRegister = controller1.ListUnloadByCenter(wasteBean);
        
        HttpSession session = request.getSession(true);
        session.setAttribute("bookAccept", bookAccept);
        session.setAttribute("unloadRegister", unloadRegister);
        session.setAttribute("loggedOwner", ownerBean);
        session.setAttribute("centerInfo", centerBean);
        response.sendRedirect("historyANDunloads.jsp");
	}

}
