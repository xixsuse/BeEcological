package logic.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import error.AlreadyUsedUsernameException;
import error.InexistentUsernameException;
import logic.Bean.BookingBean;
import logic.Bean.CenterBean;
import logic.Bean.CenterOwnerBean;
import logic.Bean.UserBean;
import logic.Controller.BookingController;
import logic.Controller.UserController;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@WebServlet("/InsertBookingServlet")
public class InsertBookingServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean checkTime(String hour){
	    try {
	    	LocalTime.parse(hour);
	        System.out.println("Valid time string: " + hour);
	        return true;
	    }
	    catch (DateTimeParseException | NullPointerException e) {
	        System.out.println("Invalid time string: " + hour);
	        return false;
	    }
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		UserBean userBean = new UserBean();
		CenterOwnerBean ownerBean = new CenterOwnerBean();
    	CenterBean centerBean = new CenterBean();
        BookingBean bookingBean = new BookingBean();
        
        userBean.setUsername(request.getParameter("userToRegister"));
        
        ownerBean.setUsername(request.getParameter("username"));
        ownerBean.setEmailAddress(request.getParameter("mail"));
        ownerBean.setPhoneNumber(request.getParameter("ownerphone"));
        centerBean.setName(request.getParameter("centername"));
        centerBean.setAddress(request.getParameter("address"));
        centerBean.setCenterPhone(request.getParameter("centerphone"));
        
    	bookingBean.setCenter(request.getParameter("centername"));
    	bookingBean.setUser(request.getParameter("userToRegister"));
    	bookingBean.setDate(request.getParameter("date"));
    	bookingBean.setTime(request.getParameter("time"));
        
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedOwner", ownerBean);
        session.setAttribute("centerInfo", centerBean);
        
    	UserController controller = new UserController();
    	boolean result = false;
		try {
			result = controller.CheckRegistration(userBean);
		} catch (AlreadyUsedUsernameException e1) {
			e1.printStackTrace();
		}
    	if(result) {
    		//username non esiste non posso inserire
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Username non exist in the system.');");
            out.println("location='manageBooking.jsp';");
            out.println("</script>");
            return;
    	}
    	
		if (!checkTime(bookingBean.getTime())) {
			//orario immesso invalido non posso inserire
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Insert a correct time format: [HH:MM].');");
            out.println("location='manageBooking.jsp';");
            out.println("</script>");
            return;
		}
    	
    	BookingController controller1 = new BookingController();
    	bookingBean.setStatus("W");
    	int count = controller1.VerifyBooking(bookingBean);
    	if(count!=0) {
    		//esiste prenotazione, la aggiorno accettandola
    		bookingBean.setStatus("A");
    		controller1.ModifyBooking(bookingBean);
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Booking accepted successfully.');");
            out.println("location='homeOwner.jsp';");
            out.println("</script>");
            return;
    	}
    	
    	bookingBean.setStatus("A");
		count = controller1.VerifyBooking(bookingBean);
    	if(count!=0) {
    		//prenotazione gia accettata, non posso inserire
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Booking already exist in the system.');");
            out.println("location='manageBooking.jsp';");
            out.println("</script>");
            return;
    	}    	
        
    	try {
			controller1.InsertBooking(bookingBean);
		} catch (InexistentUsernameException e) {

		}
        
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Booking accepted successfully.');");
        out.println("location='homeOwner.jsp';");
        out.println("</script>");
	}

}
