package mind.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import mind.controller.HealthController;

/**
 * Application Lifecycle Listener implementation class HandlerMappingListener
 *
 */
@WebListener
public class HandlerMappingListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent e) {
		ServletContext application = e.getServletContext();
		String fileName = application.getInitParameter("fileName");
		Map<String, HealthController> map = new HashMap<String, HealthController>();

		// 외부 properties파일 로딩 properties 객체 or resourceBundle
		// ResourceBundle은 properties 읽어오는 전용 클래스로서 확장자는 생략한다.

		ResourceBundle rb = ResourceBundle.getBundle(fileName);
		Set<String> keys = rb.keySet();
		try {
			for (String key : keys) {
				String value = rb.getString(key);
				// System.out.println(key +" = "+value);
				// String 을 -> 객체로 생성한다.
				HealthController con = (HealthController) Class.forName(value).newInstance();
	    		System.out.println(con);
				map.put(key, con);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		application.setAttribute("map", map);
	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
