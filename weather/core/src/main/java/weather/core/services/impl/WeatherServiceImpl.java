package weather.core.services.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weather.core.constants.WeatherInformation;
import weather.core.services.WeatherInfo;

/**
 * WeatherServiceImpl.java -class implementing the WeatherInfo interface to get
 * the weather details for city
 * 
 * @author Aviva
 * @version 1.0
 */
@Component(label = "Weather Service Impl", description = "Weather Description", metatype = true, immediate = false)
@Properties({ @Property(label = "Weather", name = "Weather.vendor", value = "Weather", propertyPrivate = true) })
@Service
public class WeatherServiceImpl implements WeatherInfo {

	private Logger log = LoggerFactory.getLogger(WeatherServiceImpl.class);

	private String username = null;
	private String password = null;
	private String apiKey = null;

	@Activate
	public void activate(Map<String, Object> properties) {
		username = PropertiesUtil.toString(properties.get("username"), "");
		password = PropertiesUtil.toString(properties.get("password"), "");
		apiKey = PropertiesUtil.toString(properties.get("apiKey"), "");
	}

	/**
	 * @author Aviva
	 * @param City
	 * @return
	 * @description calling getweatherData method to fetch the details from rest
	 *              API
	 */
	@Override
	public JSONObject getWeatherDetails(String cityName) {

		String wJson = getweatherData(cityName);
		JSONObject weatherJson = null;
		JSONObject currentWeather = new JSONObject();
		try {
			if (null != wJson) {
				weatherJson = new JSONObject(wJson);
				currentWeather.put("TempInCenti", weatherJson.getJSONObject(WeatherInformation.CURRENT_WEATHER)
						.get(WeatherInformation.TEMP_CENTIGRADE));
				currentWeather.put("TempInFareh", weatherJson.getJSONObject(WeatherInformation.CURRENT_WEATHER)
						.get(WeatherInformation.TEMP_FAREHNITE));
			} else {
				currentWeather.put("TempInCenti", "NA");
				currentWeather.put("TempInFareh", "NA");
			}
		} catch (Exception e) {
			log.error("Error in Weather ServiceImpl", e);
		}
		return currentWeather;
	}
	/**
	 * @author Aviva
	 * @param City
	 * @return
	 * @description Consuming REST API and fetching the weather details
	 */
	public String getweatherData(String cityName) {
		String response = null;
		String Url = "http://api.apixu.com/v1/current.json?key=" + apiKey + "&q=" + cityName;
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(Url);
		HostConfiguration config = client.getHostConfiguration();
		config.setProxy(WeatherInformation.PROXY_HOST, WeatherInformation.PROXY_PORT);
		Credentials credentials = new UsernamePasswordCredentials(username, password);
		AuthScope authScope = new AuthScope(WeatherInformation.PROXY_HOST, WeatherInformation.PROXY_PORT);
		client.getState().setProxyCredentials(authScope, credentials);
		try {
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				response = method.getResponseBodyAsString();
				return response;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return null;
	}
}