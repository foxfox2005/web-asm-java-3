package controller.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classs.Users;
import dao.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signin")
public class SigninServlet extends HttpServlet {
    private final UsersDAO usersDAO = new UsersDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if the user is already logged in
        if (request.getSession().getAttribute("currentUser") != null) {
            // Redirect to the home page
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        request.getRequestDispatcher("/views/client/signin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> values = new HashMap<>();
        values.put("username", request.getParameter("username"));
        values.put("password", request.getParameter("password"));
        Map<String, List<String>> violations = new HashMap<>();
        Users user = usersDAO.selectById(values.get("username"));

        if (user == null) {
            violations.put("usernameViolations", Arrays.asList("Tên đăng nhập không tồn tại."));
        }

        if (user != null && !user.getPassword().equals(values.get("password"))) {
            violations.put("passwordViolations", Arrays.asList("Mật khẩu không chính xác."));
        }
        if (!violations.isEmpty()) {
            request.setAttribute("values", values);
            request.setAttribute("violations", violations);
            request.getRequestDispatcher("/views/client/signin.jsp").forward(request, response);
            return;
        }
        if (user != null && user.getPassword().equals(values.get("password"))) {
            request.getSession().setAttribute("currentUser", user);
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            request.setAttribute("values", values);
            request.setAttribute("violations", violations);
            request.getRequestDispatcher("/views/client/signin.jsp").forward(request, response);
        }
    }
}