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

import model.Post;
import model.User;
import service.DbPost;

@WebServlet("/PostServ")
public class PostServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostServ() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postText = request.getParameter("posttext");
		String nextURL = "/error.jsp";
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			nextURL = "/login.jsp";
			session.invalidate();
		} else {
			User user = (User)session.getAttribute("user");
			
			// insert the post
			Post post = new Post();
			post.setUser(user);
			Date postdate = Calendar.getInstance().getTime();
			post.setDate(postdate);
			post.setText(postText);
			DbPost.insert(post);
			nextURL = "/Newsfeed";
			
		}
		
		getServletContext().getRequestDispatcher(nextURL).forward(request, response);
	}

}
