package mind.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mind.model.dto.GymDTO;
import mind.service.HealthService;

public class GymDetailController implements HealthController {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		ModelAndView mv = new ModelAndView();
		//NumberFormatException 주의
		int code = Integer.parseInt(request.getParameter("code"));
		GymDTO gymDTO = HealthService.selectGymByCode(code);
		request.setAttribute("gym", gymDTO);
		
		return mv;
	}
}
