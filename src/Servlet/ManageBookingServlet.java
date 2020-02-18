package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.BookingBean;
import Bean.CenterBean;
import Bean.CenterOwnerBean;
import Controller.BookingController;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ManageBookingServlet")
public class ManageBookingServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	CenterOwnerBean ownerBean = new CenterOwnerBean();
    	CenterBean centerBean = new CenterBean();
        BookingBean bookingBean = new BookingBean();
        ownerBean.setUsername(request.getParameter("username"));
        ownerBean.setEmailAddress(request.getParameter("mail"));
        ownerBean.setPhoneNumber(request.getParameter("ownerphone"));
        centerBean.setName(request.getParameter("centername"));
        centerBean.setAddress(request.getParameter("address"));
        centerBean.setCenterPhone(request.getParameter("centerphone"));
    	bookingBean.setCenter(request.getParameter("centername"));
    	bookingBean.setStatus("W");
        BookingController controller = new BookingController();
        ArrayList<BookingBean> bookWait = controller.BookingListByCenter(bookingBean);
        
        HttpSession session = request.getSession(true);
        session.setAttribute("bookWait", bookWait);
        session.setAttribute("loggedOwner", ownerBean);
        session.setAttribute("centerInfo", centerBean);
        response.sendRedirect("manageBooking.jsp");
	}

}
