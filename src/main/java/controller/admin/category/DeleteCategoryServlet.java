package controller.admin.category;

import java.io.IOException;

import classs.Categories;
import dao.CategoriesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/categoryManager/delete")
public class DeleteCategoryServlet extends HttpServlet {
	private final CategoriesDAO categoriesDAO = new CategoriesDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		Categories categories = categoriesDAO.selectById(id);
		if (categories != null) {
			try {
				String successMessage = String.format("Xóa thể loại #%s thành công!", id);
				categoriesDAO.delete(id);
				request.getSession().setAttribute("successMessage", successMessage);
			} catch (Exception e) {
				String errorMessage = String.format("Xóa thể loại #%s thất bại!", id);
				request.getSession().setAttribute("errorMessage", errorMessage);
			}
		}
		response.sendRedirect(request.getContextPath() + "/admin/categoryManager");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
