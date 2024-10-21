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

@WebServlet("/admin/newslettersManager/delete")
public class DeleteNewsletterServlet extends HttpServlet {
	private final NewsletterDAO newslettersDAO = new NewsletterDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Newsletters newsletters = newslettersDAO.selectById(id);
		if (newsletters != null) {
			try {
				String successMessage = String.format("Xóa Newsletter #%s thành công!", id);
				newslettersDAO.delete(id);
				request.getSession().setAttribute("successMessage", successMessage);
			} catch (Exception e) {
				String errorMessage = String.format("Xóa Newsletter #%s thất bại!", id);
				request.getSession().setAttribute("errorMessage", errorMessage);
			}
		}
		response.sendRedirect(request.getContextPath() + "/admin/newslettersManager");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
