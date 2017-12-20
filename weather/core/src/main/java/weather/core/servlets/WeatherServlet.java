package weather.core.servlets;

/**  
* WeatherServlet.java -servlet returning the weather information to front end   
* @author  Aviva
* @version 1.0 
*/
import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weather.core.services.WeatherInfo;

@SuppressWarnings("serial")
@SlingServlet(paths = "/bin/aviva/weather")
public class WeatherServlet extends SlingSafeMethodsServlet {

	private Logger log = LoggerFactory.getLogger(WeatherServlet.class);
	@Reference
	private WeatherInfo weatherInfo;
	/**
	 * @author Aviva
	 * @param request
	 * @return
	 * @description Method fetching weather information from service and passing
	 *              it to front end
	 */
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
		String city = request.getParameter("value1");
		if (null != city) {
			log.info("Input Value: " + city);
			log.info("Response Data: " + weatherInfo.getWeatherDetails(city));
			response.getWriter().write(weatherInfo.getWeatherDetails(city).toString());
		}
	}
	public void setWeatherInfo(WeatherInfo weatherInfo) {
		this.weatherInfo = weatherInfo;
	}
}