package mind.controller;

import java.io.IOException;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
//url 진입점 = front
@WebServlet(urlPatterns = { "/front" }, loadOnStartup = 1)
public class DispatcherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map<String, HealthController> map;

	@Override
	public void init() throws ServletException {
		map = (Map<String, HealthController>) getServletContext().getAttribute("map");

	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// command = view에서 name=command
		String key = request.getParameter("command");
//		System.out.println(key);

		if (key == null) {
			key = "list";
		}
		
		try {
			ModelAndView mv = map.get(key).handleRequest(request, response);

			if (mv.isRedirect()) {// redirect방식
				response.sendRedirect(mv.getViewName());
			} else {
				request.getRequestDispatcher(mv.getViewName()).forward(request, response);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("errorView/error.jsp").forward(request, response);
		}
	}
}
