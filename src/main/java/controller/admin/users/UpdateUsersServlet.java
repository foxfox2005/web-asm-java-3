package controller.admin.users;

import java.io.IOException;
import java.text.SimpleDateFormat;
import classs.Users;
import dao.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/usersManager/update")
public class UpdateUsersServlet extends HttpServlet {
	private final UsersDAO usersDAO = new UsersDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Users users = usersDAO.selectById(id);
		if (users != null) {
			request.setAttribute("user", users);
			request.getRequestDispatcher("/views/admin/user/updateUser.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/usersManager");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String successMessage = "Cập nhật thành công!";
		String errorMessage = "Cập nhật thất bại!";
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
			if (usersDAO.selectById(users.getId()) != null) {
				usersDAO.update(users);
				request.setAttribute("user", users);
				request.setAttribute("successMessage", successMessage);
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/usersManager");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/views/admin/user/updateUser.jsp").forward(request, response);
	}
}
