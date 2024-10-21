package controller.client;

import dao.NewsDAO;
import classs.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/newsDetails")
public class NewsDetailsServlet extends HttpServlet {
    private NewsDAO newsDAO = new NewsDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            News news = newsDAO.findById(id);
            request.setAttribute("news", news);
        }
        request.getRequestDispatcher("/views/client/newsDetails.jsp").forward(request, response);
    }
}