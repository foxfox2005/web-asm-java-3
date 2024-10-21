package controller.client;

import java.io.IOException;
import dao.CategoriesDAO;
import dao.NewsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final NewsDAO newsDAO = new NewsDAO();
	private final CategoriesDAO categoriesDAO = new CategoriesDAO();

	public HomeController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("newsHome", newsDAO.showNewsHome());
		request.setAttribute("categories", categoriesDAO.selectAll());
		request.getRequestDispatcher("/views/client/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
