package controller.admin.users;

import java.io.IOException;

import classs.Categories;
import classs.Users;
import dao.CategoriesDAO;
import dao.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/usersManager/delete")
public class DeleteUsersServlet extends HttpServlet {
	private final UsersDAO usersDAO = new UsersDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		Users users = usersDAO.selectById(id);
		if (users != null) {
			try {
				String successMessage = String.format("Xóa thể loại #%s thành công!", id);
				usersDAO.delete(id);
				request.getSession().setAttribute("successMessage", successMessage);
			} catch (Exception e) {
				String errorMessage = String.format("Xóa thể loại #%s thất bại!", id);
				request.getSession().setAttribute("errorMessage", errorMessage);
			}
		}
		response.sendRedirect(request.getContextPath() + "/admin/usersManager");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
