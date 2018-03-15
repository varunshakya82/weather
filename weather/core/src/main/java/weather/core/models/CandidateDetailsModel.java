package weather.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weather.core.services.impl.WeatherServiceImpl;

@Model(adaptables=Resource.class)
public class CandidateDetailsModel {

	private Logger log = LoggerFactory.getLogger(CandidateDetailsModel.class);
	@Inject
	@Default(values="default Name")
	private String fname;
	
	@Inject
	@Default(values="default Last Name")
	private String lname;
	
	@Inject
	@Default(values="default City")
	private String city;
	
	@Inject
	@Named("sling:resourceType")
	private String slingResource;

	public String getSlingResource() {
		return slingResource;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getCity() {
		return city;
	}
	
	@PostConstruct
    protected void sayHello() {
         log.info("hello");
    }
	
	
	
}
