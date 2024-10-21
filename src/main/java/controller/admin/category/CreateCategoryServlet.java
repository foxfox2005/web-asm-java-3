package controller.admin.category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classs.Categories;
import dao.CategoriesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/categoryManager/create")
public class CreateCategoryServlet extends HttpServlet {
	private final CategoriesDAO categoriesDAO = new CategoriesDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/admin/category/createCategory.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Categories category = new Categories();
		category.setId(String.valueOf(categoriesDAO.createId()));
		category.setName(request.getParameter("name"));

		Map<String, List<String>> violations = new HashMap<>();
		String successMessage = "Thêm thành công!";
		String errorMessage = "Thêm thất bại!";
		String nameViolations = "Vui lòng nhập tên thể loại";
		if (category.getName() == null || category.getName().trim().isEmpty()) {
			List<String> nameErrors = new ArrayList<>();
			nameErrors.add(nameViolations);
			violations.put("name", nameErrors);
		}
		categoriesDAO.insert(category);
		request.setAttribute("successMessage", successMessage);
		request.setAttribute("category", category);
		request.setAttribute("violations", violations);
		request.getRequestDispatcher("/views/admin/category/createCategory.jsp").forward(request, response);
	}
}
