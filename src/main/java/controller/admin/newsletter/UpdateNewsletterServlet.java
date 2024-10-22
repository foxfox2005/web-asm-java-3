package controller.admin.newsletter;

import java.io.IOException;

import classs.Categories;
import classs.Newsletters;
import dao.CategoriesDAO;
import dao.NewsletterDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/newslettersManager/update")
public class UpdateNewsletterServlet extends HttpServlet {
	private final NewsletterDAO newslettersDAO = new NewsletterDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Newsletters newsletters = newslettersDAO.selectById(id);
		if (newsletters != null) {
			request.setAttribute("newsletter", newsletters);
			request.getRequestDispatcher("/views/admin/newsletter/updateletter.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/newslettersManager");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Newsletters newsletters = new Newsletters();
		newsletters.setEmail(String.valueOf(request.getParameter("email")));
		newsletters.setEnabled(Boolean.valueOf(request.getParameter("enabled")));
		String successMessage = "Sửa thành công!";
		String errorMessage = "Sửa thất bại!";
		if (newslettersDAO.selectById(newsletters.getEmail()) != null) {
			newslettersDAO.update(newsletters);
			request.setAttribute("newsletter", newsletters);
			request.setAttribute("successMessage", successMessage);
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/newslettersManager");
			return;
		}

		request.getRequestDispatcher("/views/admin/newsletter/updateletter.jsp").forward(request, response);
	}
}
