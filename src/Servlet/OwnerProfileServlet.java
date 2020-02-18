package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.CenterBean;
import Bean.CenterOwnerBean;
import Controller.OwnerController;

import java.io.IOException;
import java.util.List;

@WebServlet("/OwnerProfileServlet")
public class OwnerProfileServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CenterOwnerBean ownerBean = new CenterOwnerBean();
        CenterBean centerBean = new CenterBean();
    	ownerBean.setUsername(request.getParameter("username"));
    	OwnerController controller = new OwnerController();
        
        List<String> information = controller.OwnerData(ownerBean);
        ownerBean.setName(information.get(0));
        ownerBean.setSurname(information.get(1));
        ownerBean.setEmailAddress(information.get(2));
        ownerBean.setPhoneNumber(information.get(3));
        ownerBean.setCenter(information.get(4));
        centerBean.setName(information.get(4));
        centerBean.setCenterPhone(information.get(5));
        centerBean.setCity(information.get(6));
        centerBean.setAddress(information.get(7));
        centerBean.setCAP(information.get(8));
        centerBean.setNum(information.get(9));
        
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedOwner", ownerBean);
        session.setAttribute("centerInfo", centerBean);
        response.sendRedirect("ownerProfile.jsp");
    }
}
