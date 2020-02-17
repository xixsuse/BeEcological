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
import java.io.PrintWriter;

@WebServlet("/ConfirmBookingServlet")
public class ConfirmBookingServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
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
    	bookingBean.setUser(request.getParameter("userToRegister1"));
    	bookingBean.setDate(request.getParameter("date1"));
    	bookingBean.setTime(request.getParameter("time1"));
    	bookingBean.setStatus("A");
    	
        HttpSession session = request.getSession(true);
    	
        BookingController controller = new BookingController();
    	controller.ModifyBooking(bookingBean);
        
        session.setAttribute("loggedOwner", ownerBean);
        session.setAttribute("centerInfo", centerBean);
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Booking accepted successfully.');");
        out.println("location='homeOwner.jsp';");
        out.println("</script>");
	}
}
