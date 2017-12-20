package weather.core.services.impl;

import org.apache.sling.commons.json.JSONException;
import static org.mockito.Mockito.when;
import org.apache.sling.commons.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import weather.core.services.WeatherInfo;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceImplTest {

	@Mock
	private WeatherInfo serviceImpl;

	@Test
	public void verifyWeatherDetailsValidCity() throws JSONException {
		JSONObject testJSON = new JSONObject();
		testJSON.put("TempInCenti", 20.1);
		testJSON.put("TempInFareh", 68.2);
		when(serviceImpl.getWeatherDetails("pune")).thenReturn(testJSON);
		JSONObject weatherJson = serviceImpl.getWeatherDetails("pune");
		assertNotNull(weatherJson);

	}

	@Test
	public void verifyWeatherDetailsInValidCity() throws JSONException {
		JSONObject testJSON = new JSONObject();
		testJSON.put("TempInCenti", "NA");
		testJSON.put("TempInFareh", "NA");
		JSONObject weatherJson = serviceImpl.getWeatherDetails("XYZ");
		assertNull(weatherJson);
	}
}
