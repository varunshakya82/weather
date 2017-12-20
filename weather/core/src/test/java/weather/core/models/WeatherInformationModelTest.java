package weather.core.models;

import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class WeatherInformationModelTest {

	@Mock
	private WeatherInformationModel weatherInformationModel;

	@Test
	public void testGetWeatherNull() {
		String weatherTitle = weatherInformationModel.getWeatherInfo();
		assertNull(weatherTitle);
	}
	@Test
	public void testGetWeatherTitleNotNull() {
		when(weatherInformationModel.getWeatherInfo()).thenReturn("Get Info");
		String weatherTitle = weatherInformationModel.getWeatherInfo();
		assertNotNull(weatherTitle);
		assertTrue(weatherTitle.length() > 0);
	}
	@Test
	public void testGetCityNull() {
		String cityTitle = weatherInformationModel.getCity();
		assertNull(cityTitle);
	}
	@Test
	public void testGetCityTitleNotNull() {
		when(weatherInformationModel.getCity()).thenReturn("Get City Title");
		String cityTitle = weatherInformationModel.getCity();
		assertNotNull(cityTitle);
		assertTrue(cityTitle.length() > 0);
	}
}