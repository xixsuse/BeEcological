package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.BookingBean;
import Bean.UserBean;
import Controller.BookingController;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/UserBookingListServlet")
public class UserBookingListServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	UserBean userBean = new UserBean();
        BookingBean bookingBean = new BookingBean();
    	userBean.setUsername(request.getParameter("username"));
    	bookingBean.setUser(userBean.getUsername());
    	bookingBean.setStatus("W");
        BookingController controller = new BookingController();
        ArrayList<BookingBean> bookingWait = controller.BookingListByUser(bookingBean);
        bookingBean.setStatus("A");
        ArrayList<BookingBean> bookingAccepted = controller.BookingListByUser(bookingBean);
        bookingBean.setStatus("D");
        ArrayList<BookingBean> bookingRefuse = controller.BookingListByUser(bookingBean);
        
        HttpSession session = request.getSession(true);
        session.setAttribute("bookWait", bookingWait);
        session.setAttribute("bookAccept", bookingAccepted);
        session.setAttribute("bookRefuse", bookingRefuse);
        session.setAttribute("loggedUser", userBean);
        response.sendRedirect("userBookingList.jsp");
	}

}
