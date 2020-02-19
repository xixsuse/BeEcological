package logic.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.Bean.UserBean;
import logic.
Controller.UserController;

import java.io.IOException;
import java.util.List;

@WebServlet("/ShopServlet")
public class ShopServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
        UserBean userBean = new UserBean();
        userBean.setUsername(req.getParameter("param"));
        UserController controller = new UserController();
        List<String> userInformation = controller.UserInformation(userBean);
        userBean.setName(userInformation.get(0));
        userBean.setSurname(userInformation.get(1));
        userBean.setEmailAddress(userInformation.get(2));
        userBean.setPhoneNumber(userInformation.get(3));
        userBean.setEcopoints(Integer.parseInt(userInformation.get(4)));
        
        session.setAttribute("loggedUser", userBean);
        resp.sendRedirect("shop.jsp");
    }
}
