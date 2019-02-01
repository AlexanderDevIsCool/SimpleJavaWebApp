package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.DbUser;

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddUser() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		String userPasswrod = request.getParameter("userPassword");
		String userMotto = request.getParameter("userMotto");
		String nextURL = "/error.jsp";
		System.out.println("User email: " + userEmail);
		User user = DbUser.getUserByEmail(userEmail);
		if(user == null) {
			user = new User();
			user.setName(userName);
			user.setEmail(userEmail);
			user.setPassword(userPasswrod);
			Date joindate = Calendar.getInstance().getTime();
			user.setJoindate(joindate);
			user.setMotto(userMotto);
			DbUser.insert(user);
			nextURL = "/home.jsp";
		} else {
			String message = "You have an accout - ";
			request.setAttribute("message", message);
			nextURL = "/login.jsp";
		}
		
		session.setAttribute("user", user);
		
		getServletContext().getRequestDispatcher(nextURL).forward(request, response);
	}

}
