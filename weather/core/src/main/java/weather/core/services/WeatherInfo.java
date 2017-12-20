package weather.core.services;

import org.apache.sling.commons.json.JSONObject;

public interface WeatherInfo {

	public JSONObject getWeatherDetails(String cityName);
}
