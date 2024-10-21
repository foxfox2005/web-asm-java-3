package controller.admin;

import java.io.IOException;

import classs.Users;
import dao.NewsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private final NewsDAO newsDAO = new NewsDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users users = (Users) request.getSession().getAttribute("currentUser");
		if (users.isRole()) {
			request.setAttribute("news", newsDAO.selectAll());
		} else {
			request.setAttribute("news", newsDAO.selectByIdUser(users.getId()));
		}
		request.getRequestDispatcher("/views/admin/news/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
