package controller.admin.newsletter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classs.Categories;
import classs.Newsletters;
import dao.CategoriesDAO;
import dao.NewsletterDAO;
import dao.NewsletterDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/newslettersManager/create")
public class CreateNewsletterServlet extends HttpServlet {
	private final NewsletterDAO newslettersDAO = new NewsletterDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/admin/newsletter/createletter.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Newsletters newsletters = new Newsletters();
		newsletters.setEmail((String) request.getParameter("email"));
		newsletters.setEnabled(Boolean.valueOf(request.getParameter("enabled")));

		String successMessage = "Thêm thành công!";
		String errorMessage = "Thêm thất bại!";
		String nameViolations = "Email này đã có trên hệ thông";
		if (newslettersDAO.selectById(newsletters.getEmail()) != null) {
			request.setAttribute("nameViolations", nameViolations);
		} else {
			if (newslettersDAO.insert(newsletters) > 0) {
				request.setAttribute("successMessage", successMessage);
			} else {
				request.setAttribute("errorMessage", errorMessage);
			}
		}
		request.setAttribute("newsletter", newsletters);
		request.getRequestDispatcher("/views/admin/newsletter/createletter.jsp").forward(request, response);
	}
}
