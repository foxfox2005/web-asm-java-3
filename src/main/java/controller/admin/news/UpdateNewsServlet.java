package controller.admin.news;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import classs.News;
import dao.CategoriesDAO;
import dao.NewsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/admin/newsManager/update")
@MultipartConfig
public class UpdateNewsServlet extends HttpServlet {
	private final NewsDAO newsDAO = new NewsDAO();
	private final CategoriesDAO categoriesDAO = new CategoriesDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		News news = newsDAO.selectById(id);
		if (news != null) {
			request.setAttribute("categories", categoriesDAO.selectAll());
			request.setAttribute("news", news);
			request.getRequestDispatcher("/views/admin/news/updateNews.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/admin");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		News news = newsDAO.selectById(id);
		news.setCategoriesId((String) request.getParameter("categoriesId"));
		news.setContent((String) request.getParameter("content"));
		news.setHome(
				request.getParameter("home") == null ? news.getHome() : Boolean.valueOf(request.getParameter("home")));
		news.setTitle((String) request.getParameter("title"));

		String successMessage = "Sửa thành công!";
		String errorMessage = "Sửa thất bại!";
		if (newsDAO.selectById(news.getId()) != null) {
			Part imagePart = request.getPart("image");
			if (imagePart != null && imagePart.getSize() > 0) {
				String imagePath = getServletContext().getRealPath("/static/files/" + news.getImage());
				File imageFile = new File(imagePath);
				if (imageFile.exists()) {
					boolean deleted = imageFile.delete();
					if (!deleted) {
						System.out.println("Failed to delete image: " + imagePath);
					}
				}
				String originalFileName = imagePart.getSubmittedFileName();
				String fileExtension = "";
				int i = originalFileName.lastIndexOf('.');
				if (i > 0) {
					fileExtension = originalFileName.substring(i);
				}
				String randomFileName = UUID.randomUUID().toString() + fileExtension;

				String uploadDir = getServletContext().getRealPath("/static/files");
				File uploadDirFile = new File(uploadDir);
				if (!uploadDirFile.exists()) {
					uploadDirFile.mkdirs(); // Tạo thư mục nếu chưa tồn tại
				}
				File file = new File(uploadDir, randomFileName);
				imagePart.write(file.getAbsolutePath()); // Lưu tệp ảnh mới

				news.setImage(randomFileName);
			}
			if (newsDAO.update(news) > 0) {
				request.setAttribute("categories", categoriesDAO.selectAll());
				request.setAttribute("news", news);
				request.setAttribute("successMessage", successMessage);
			} else {
				request.setAttribute("errorMessage", errorMessage);
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		}

		request.getRequestDispatcher("/views/admin/news/updateNews.jsp").forward(request, response);
	}
}
