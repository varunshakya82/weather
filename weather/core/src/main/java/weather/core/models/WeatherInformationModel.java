package weather.core.models;


import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

/**
 * WeatherInformationModel.java -Class working as model to "weathercomponent" ,
 * get the city and weather titles.
 * 
 * @author Aviva
 * @version 1.0
 */
@Model(adaptables = Resource.class)
public class WeatherInformationModel {

	@Inject
	@Default(values = "Get Weather details")
	private String weatherInfo;
	@Inject
	@Default(values = "Enter the city")
	private String city;

	public String getWeatherInfo() {
		return weatherInfo+"************";
	}
	
	public String getCity() {
		return city;
	}
}
