package logic.Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.Bean.BookingBean;
import logic.Bean.UserBean;
import logic.Controller.BookingController;
import logic.Controller.UserController;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/BookingRequestServlet")
public class BookingRequestServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		UserBean userBean = new UserBean();
    	BookingBean bookingBean = new BookingBean();
        userBean.setUsername(request.getParameter("username"));
        
        UserController controller = new UserController();
        boolean result = controller.CheckRegistration(userBean);
        HttpSession session = request.getSession(true);
        
        //se false l'username esiste nel sistema
        if(!result) {
            bookingBean.setUser(userBean.getUsername());
            bookingBean.setCenter(request.getParameter("centername"));
            bookingBean.setDate(request.getParameter("date"));
            bookingBean.setTime(request.getParameter("time"));
            bookingBean.setStatus("W");
            BookingController controller1 = new BookingController();
            controller1.InsertBooking(bookingBean);
        	session.setAttribute("loggedUser", userBean);
		    out.println("<script type=\"text/javascript\">");
		    out.println("alert('Your booking request is completed successfully!');");
		    out.println("location='homepage.jsp';");
		    out.println("</script>");
        }
        else {
        	request.setAttribute("alertMsg", "You must login in the system for book an unload.");
            RequestDispatcher rd = request.getRequestDispatcher("/loginUser.jsp");
            rd.include(request, response);
        }
	}
}