package controller;

import java.io.IOException;
import classs.Newsletters;
import dao.NewsletterDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.EmailHelper;

@WebServlet("/newsletter/subscribe")
public class SubscribeNewsletterServlet extends HttpServlet {
    private final NewsletterDAO newsletterDAO = new NewsletterDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        Newsletters newsletter = new Newsletters();
        newsletter.setEmail(email);
        newsletter.setEnabled(true);

        if (newsletterDAO.selectById(email) == null) {
            if (newsletterDAO.insert(newsletter) > 0) {
                EmailHelper.sendEmail(email, "Subscription Confirmation",
                        "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "<style>" +
                        "body { font-family: Arial, sans-serif; }" +
                        ".container { max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px; }" +
                        ".header { background-color: #f4f4f4; padding: 10px; text-align: center; border-bottom: 1px solid #ddd; }" +
                        ".content { padding: 20px; }" +
                        ".footer { background-color: #f4f4f4; padding: 10px; text-align: center; border-top: 1px solid #ddd; }" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<div class='container'>" +
                        "<div class='header'>" +
                        "<h1>Subscription Successful</h1>" +
                        "</div>" +
                        "<div class='content'>" +
                        "<p>Thank you for subscribing!</p>" +
                        "<p>You will receive updates whenever a new post is published.</p>" +
                        "</div>" +
                        "<div class='footer'>" +
                        "<p>&copy; Fox Fox</p>" +
                        "</div>" +
                        "</div>" +
                        "</body>" +
                        "</html>");
                response.sendRedirect(request.getContextPath() + "/views/client/thankyou.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/views/client/subscription-failure.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/views/client/subscription-exists.jsp");
        }
    }
}