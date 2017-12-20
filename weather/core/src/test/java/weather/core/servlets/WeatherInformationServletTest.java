package weather.core.servlets;

import static org.mockito.Mockito.when;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.commons.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import weather.core.services.*;

@RunWith(MockitoJUnitRunner.class)
public class WeatherInformationServletTest {

	@Mock
	private SlingHttpServletRequest request;
	@Mock
	private SlingHttpServletResponse response;
	@Mock
	JSONObject jsonObject;
	@Mock
	WeatherInfo weather;
	@Mock
	PrintWriter printWriter;
	@Mock
	private WeatherServlet informationServlet;

	@Test
	public void checkResponseForCity() throws ServletException, IOException {
		informationServlet.setWeatherInfo(weather);
		when(request.getParameter("value1")).thenReturn("pune");
		when(response.getWriter()).thenReturn(printWriter);
		when(weather.getWeatherDetails("pune")).thenReturn(jsonObject);
		informationServlet.doGet(request, response);
	}
}
