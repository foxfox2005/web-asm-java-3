package controller.client;

import dao.NewsletterDAO;
import classs.Newsletters;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private final NewsletterDAO newsletterDAO = new NewsletterDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Newsletters> topViewed = newsletterDAO.getTopViewedNewsletters(5);
        List<Newsletters> latest = newsletterDAO.getLatestNewsletters(5);
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        List<Newsletters> userViewed = userId != null ? newsletterDAO.getUserViewedNewsletters(userId, 5) : new ArrayList<>();

        request.setAttribute("topViewed", topViewed);
        request.setAttribute("latest", latest);
        request.setAttribute("userViewed", userViewed);
        request.getRequestDispatcher("/views/client/home.jsp").forward(request, response);
    }
}