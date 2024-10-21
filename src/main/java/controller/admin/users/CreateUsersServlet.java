package controller.admin.users;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classs.Categories;
import classs.Users;
import dao.CategoriesDAO;
import dao.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/usersManager/create")
public class CreateUsersServlet extends HttpServlet {
	private final UsersDAO usersDAO = new UsersDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/admin/user/createUser.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String successMessage = "Thêm thành công!";
		String errorMessage = "Thêm thất bại!";
		String nameViolations = "Mã username đã tồn tại";
		try {
			Users users = new Users();
			users.setId((String) request.getParameter("id"));
			users.setEmail((String) request.getParameter("email"));
			users.setFullname((String) request.getParameter("fullname"));
			users.setGender(Boolean.valueOf(request.getParameter("gender")));
			users.setMobile((String) request.getParameter("mobile"));
			users.setPassword((String) request.getParameter("password"));
			users.setRole(Boolean.valueOf(request.getParameter("role")));
			users.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday")));
			if (usersDAO.selectById(users.getId()) == null) {
				usersDAO.insert(users);
				request.setAttribute("successMessage", successMessage);
			} else {
				request.setAttribute("user", users);
				request.setAttribute("nameViolations", nameViolations);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/views/admin/user/createUser.jsp").forward(request, response);
	}
}
