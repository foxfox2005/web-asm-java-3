package controller.admin.category;

import java.io.IOException;

import dao.CategoriesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/categoryManager")
public class CategoryManagerServlet extends HttpServlet {
	private final CategoriesDAO categoriesDAO = new CategoriesDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("categories", categoriesDAO.selectAll());
		request.getRequestDispatcher("/views/admin/category/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
