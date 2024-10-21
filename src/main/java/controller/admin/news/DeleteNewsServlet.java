package controller.admin.news;

import java.io.File;
import java.io.IOException;

import classs.Categories;
import classs.News;
import dao.CategoriesDAO;
import dao.NewsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/newsManager/delete")
public class DeleteNewsServlet extends HttpServlet {
	private final NewsDAO newsDAO = new NewsDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		News news = newsDAO.selectById(id);
		if (news != null) {
			try {
				String successMessage = String.format("Xóa tin tức #%s thành công!", id);
				if (news.getImage() != null && !news.getImage().isEmpty()) {
					String imagePath = getServletContext().getRealPath("/static/files/" + news.getImage());
					File imageFile = new File(imagePath);

					if (imageFile.exists()) {
						boolean deleted = imageFile.delete();
						if (!deleted) {
							System.out.println("Failed to delete image: " + imagePath);
						}
					}
				}
				newsDAO.delete(id);
				request.getSession().setAttribute("successMessage", successMessage);
			} catch (Exception e) {
				String errorMessage = String.format("Xóa tin tức #%s thất bại!", id);
				request.getSession().setAttribute("errorMessage", errorMessage);
			}
		}
		response.sendRedirect(request.getContextPath() + "/admin");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
