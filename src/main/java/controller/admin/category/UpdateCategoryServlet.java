package controller.admin.category;

import java.io.IOException;

import classs.Categories;
import dao.CategoriesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/categoryManager/update")
public class UpdateCategoryServlet extends HttpServlet {
	private final CategoriesDAO categoriesDAO = new CategoriesDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Categories categories = categoriesDAO.selectById(id);
		if (categories != null) {
			request.setAttribute("category", categories);
			request.getRequestDispatcher("/views/admin/category/updateCategory.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/categoryManager");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Categories category = new Categories();
		category.setId(String.valueOf(request.getParameter("id")));
		category.setName(request.getParameter("name"));
		String successMessage = "Sửa thành công!";
		String errorMessage = "Sửa thất bại!";
		if (categoriesDAO.selectById(category.getId()) != null) {
			categoriesDAO.update(category);
			request.setAttribute("category", category);
			request.setAttribute("successMessage", successMessage);
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/categoryManager");
			return;
		}

		request.getRequestDispatcher("/views/admin/category/updateCategory.jsp").forward(request, response);
	}
}
