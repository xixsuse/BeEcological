package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.CenterOwnerBean;
import Controller.OwnerController;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DeleteOwnerServlet")
public class DeleteOwnerServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		CenterOwnerBean ownerBean = new CenterOwnerBean();
        
        ownerBean.setUsername(request.getParameter("username"));
        OwnerController controller = new OwnerController();
        controller.deleteAccount(ownerBean);
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedOwner", null);
        session.setAttribute("centerInfo", null);
        
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Account deleted successfully.');");
        out.println("location='homepage.jsp';");
        out.println("</script>");
	}

}