package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.DbUser;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usermail = request.getParameter("email");
		String userpassword = request.getParameter("password");
		String action = request.getParameter("action");
		String nextURL = "/error.jsp";
		
		HttpSession session = request.getSession();
		
		if(action.contentEquals("logout")) {
			session.invalidate();
			nextURL = "/login.jsp";
		} else {
			if(DbUser.isValidUser(usermail, userpassword)) {
				User user = DbUser.getUserByEmail(usermail);
				session.setAttribute("user", user);
				String gravatarURL = DbUser.getGravatarURL(usermail, 30);
				session.setAttribute("gravatarURL", gravatarURL);
				nextURL = "/home.jsp";
			} else {
				nextURL = "/login.jsp";
			}
		}
		
		getServletContext().getRequestDispatcher(nextURL).forward(request, response);
	}

}
