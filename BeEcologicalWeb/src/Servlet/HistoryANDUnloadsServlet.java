package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.BookingBean;
import Bean.UserBean;
import Bean.WasteUnloadedBean;
import Controller.BookingController;
import Controller.WasteUnloadedController;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/HistoryANDUnloadsServlet")
public class HistoryANDUnloadsServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	UserBean userBean = new UserBean();
        BookingBean bookingBean = new BookingBean();
        WasteUnloadedBean wasteBean = new WasteUnloadedBean();
        userBean.setUsername(request.getParameter("username"));
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
        session.setAttribute("loggedUser", userBean);
        response.sendRedirect("historyANDunloads.jsp");
	}

}
