package logic.Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import error.EmptyFieldException;
import error.InexistentUsernameException;
import logic.Controller.UserController;
import logic.bean.UserBean;

import java.io.IOException;

@WebServlet("/LoginUserServlet")
public class LoginUserServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean result = false;
    	UserBean userBean = new UserBean();
        userBean.setUsername(req.getParameter("username"));
        userBean.setPassword(req.getParameter("password"));
        UserController controller = new UserController();
        try {
			result = controller.Login(userBean);
		} catch (InexistentUsernameException | EmptyFieldException e) {

		}

        if (!result) {
            req.setAttribute("alertMsg", "Not valid login credentials.");
            RequestDispatcher rd = req.getRequestDispatcher("/loginUser.jsp");
            rd.include(req, resp);
        } else {
        	System.out.println("login con "+userBean.getUsername()+" "+userBean.getPassword());
            HttpSession session = req.getSession(true);
            session.setAttribute("loggedUser", userBean);
            resp.sendRedirect("homepage.jsp");
        }
    }
}
