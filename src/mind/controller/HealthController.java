package mind.controller;

import java.io.IOException;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HealthController {
	
	ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException;
	
}
