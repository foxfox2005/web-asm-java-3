package controller.client;

import dao.SubscriptionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.EmailHelper;

import java.io.IOException;

@WebServlet("/subscribe")
public class SubscribeServlet extends HttpServlet {
    private final SubscriptionDAO subscriptionDAO = new SubscriptionDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        if (email != null && !email.isEmpty()) {
            subscriptionDAO.addSubscription(email);
            String emailContent = "<!DOCTYPE html>" +
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
                "</html>";
            EmailHelper.sendEmail(email, "Subscription Successful", emailContent);
            response.sendRedirect(request.getContextPath() + "/views/client/thankyou.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/subscribe?error=invalidEmail");
        }
    }
}