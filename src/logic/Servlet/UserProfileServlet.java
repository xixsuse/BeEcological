package logic.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.Bean.UserBean;
import logic.Bean.WasteUnloadedBean;
import logic.Controller.UserController;
import logic.Controller.WasteUnloadedController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean userBean = new UserBean();
    	userBean.setUsername(request.getParameter("username"));
    	UserController controller = new UserController();
        WasteUnloadedController controller1 = new WasteUnloadedController();
        
        List<String> information = controller.UserInformation(userBean);
        userBean.setName(information.get(0));
        userBean.setSurname(information.get(1));
        userBean.setEmailAddress(information.get(2));
        userBean.setPhoneNumber(information.get(3));
        userBean.setEcopoints(Integer.parseInt(information.get(4)));
        
        ArrayList<WasteUnloadedBean> listUnloadUser = controller1.ListUnloadByUser(userBean);
        
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedUser", userBean);
        session.setAttribute("listUnloadUser", listUnloadUser);
        response.sendRedirect("userProfile.jsp");
    }
}
