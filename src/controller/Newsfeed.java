package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Post;
import service.DbPost;

@WebServlet("/Newsfeed")
public class Newsfeed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Newsfeed() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long filterByUserID = 0;
		String searchText = "";
		String nextURL = "/error.jsp";
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null) {
			nextURL = "/login.jsp";
			session.invalidate();
			response.sendRedirect(request.getContextPath() + nextURL);
			return;
		}
		
		List<Post> posts = null;
		
		if(request.getParameter("userid") != null && !request.getParameter("userid").isEmpty()){
			posts = DbPost.postsOfUser(filterByUserID);
		} else if(request.getParameter("searchtext") != null && !request.getParameter("searchtext").isEmpty()) {
			searchText = request.getParameter("searchtext").toString();
			posts = DbPost.searchPosts(searchText);
		} else {
			posts = DbPost.post();
		}
		
		request.setAttribute("posts", posts);
		nextURL = "/newsfeed.jsp";
		getServletContext().getRequestDispatcher(nextURL).forward(request, response);
	}

}




















