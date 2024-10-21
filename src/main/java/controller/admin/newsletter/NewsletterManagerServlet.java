package controller.admin.newsletter;

import java.io.IOException;


import dao.NewsletterDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/newslettersManager")
public class NewsletterManagerServlet extends HttpServlet {
	private final NewsletterDAO newsletterDAO = new NewsletterDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("newsletters", newsletterDAO.selectAll());
		request.getRequestDispatcher("/views/admin/newsletter/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
