package controller.admin.news;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import classs.News;
import classs.Users;
import dao.CategoriesDAO;
import dao.NewsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/admin/newsManager/create")
@MultipartConfig
public class CreateNewsServlet extends HttpServlet {
    private final NewsDAO newsDAO = new NewsDAO();
    private final CategoriesDAO categoriesDAO = new CategoriesDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("categories", categoriesDAO.selectAll());
        request.getRequestDispatcher("/views/admin/news/createNews.jsp").forward(request, response);
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users users = (Users) request.getSession().getAttribute("currentUser");

		String content = request.getParameter("content");
		if (content == null || content.trim().isEmpty()) {
			request.setAttribute("errorMessage", "Content cannot be empty!");
			request.setAttribute("categories", categoriesDAO.selectAll());
			request.getRequestDispatcher("/views/admin/news/createNews.jsp").forward(request, response);
			return;
		}

		News news = new News();
		news.setId(newsDAO.randomId());
		news.setCategoriesId(request.getParameter("categoriesId"));
		news.setContent(content);
		news.setHome(Boolean.valueOf(request.getParameter("home")));
		news.setPostedDate(new Date());
		news.setTitle(request.getParameter("title"));
		news.setUsersId(users.getId());
		news.setViewCount(0);

		while (newsDAO.selectById(news.getId()) != null) {
			news.setId(newsDAO.randomId());
		}

		Part photo = request.getPart("image");
		if (photo != null && photo.getSize() > 0) {
			String originalFileName = photo.getSubmittedFileName();
			String fileExtension = "";
			int i = originalFileName.lastIndexOf('.');
			if (i > 0) {
				fileExtension = originalFileName.substring(i);
			}
			String randomFileName = UUID.randomUUID().toString() + fileExtension;

			String uploadDir = getServletContext().getRealPath("/static/files");
			File uploadDirFile = new File(uploadDir);
			if (!uploadDirFile.exists()) {
				uploadDirFile.mkdirs();
			}
			File file = new File(uploadDir, randomFileName);
			photo.write(file.getAbsolutePath());

			news.setImage(randomFileName);
		}

		// Debugging: Log the news object
		System.out.println("News: " + news);

		if (newsDAO.insert(news) > 0) {
			request.setAttribute("successMessage", "Thêm thành công!");
		} else {
			request.setAttribute("errorMessage", "Thêm thất bại!");
		}

		request.setAttribute("categories", categoriesDAO.selectAll());
		request.getRequestDispatcher("/views/admin/news/createNews.jsp").forward(request, response);
	}
}