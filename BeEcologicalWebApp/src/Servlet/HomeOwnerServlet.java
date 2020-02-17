package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.CenterBean;
import Bean.CenterOwnerBean;

import java.io.IOException;

@WebServlet("/HomeOwnerServlet")
public class HomeOwnerServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	CenterOwnerBean ownerBean = new CenterOwnerBean();
    	CenterBean centerBean = new CenterBean();
    	ownerBean.setUsername(request.getParameter("username"));
    	ownerBean.setPhoneNumber(request.getParameter("ownerphone"));
    	ownerBean.setEmailAddress(request.getParameter("mail"));
    	centerBean.setName(request.getParameter("centername"));
    	centerBean.setAddress(request.getParameter("address"));
    	centerBean.setCenterPhone(request.getParameter("centerphone"));
    	
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedOwner", ownerBean);
        session.setAttribute("centerInfo", centerBean);
        response.sendRedirect("homeOwner.jsp");
	 }
}
