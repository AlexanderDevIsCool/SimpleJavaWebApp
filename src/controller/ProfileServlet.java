package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.DbUser;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProfileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nextURL = "/error.jsp";
		long userid = 0;
		String action = "";
		User profileUser = null;
		User loggedInUser = null;

		if (session.getAttribute("user") == null) {
			nextURL = "/error.jsp";
			session.invalidate();
			response.sendRedirect(request.getContextPath() + nextURL);
			return;
		}

		try {
			userid = Long.parseLong(request.getParameter("userid"));
			action = request.getParameter("action");

			if (action.contentEquals("updateprofile")) {
				long uid = Long.parseLong(request.getParameter("userid"));
				String userEmail = request.getParameter("useremail");
				String userMotto = request.getParameter("userEmail");
				User updateUser = DbUser.getUser(uid);
				updateUser.setEmail(userEmail);
				updateUser.setMotto(userMotto);
				DbUser.update(updateUser);
			}

			profileUser = DbUser.getUser(userid);
			loggedInUser = (User) session.getAttribute("user");

			if (profileUser.getId() == loggedInUser.getId()) {
				session.setAttribute("editProfile", true);
			} else {
				session.setAttribute("editProfile", false);
			}

			// populate the data in the attributes

			int imgSize = 120;
			SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
			String joinDate = sdf.format(profileUser.getJoindate());
			request.setAttribute("userid", profileUser.getId());
			request.setAttribute("userimage", DbUser.getGravatarURL(profileUser.getEmail(), imgSize));
			request.setAttribute("username", profileUser.getName());
			request.setAttribute("useremail", profileUser.getEmail());
			request.setAttribute("usermotto", profileUser.getMotto());
			request.setAttribute("userjoindate", joinDate);
			nextURL = "/profile.jsp";

		} catch (Exception e) {
			e.printStackTrace();
		}

		getServletContext().getRequestDispatcher(nextURL);

	}

}
