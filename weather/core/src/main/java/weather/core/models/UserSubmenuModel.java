package weather.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weather.core.bean.TouchMultiFieldBean;

@Model(adaptables=Resource.class)
public class UserSubmenuModel {

	private Logger log = LoggerFactory.getLogger(UserSubmenuModel.class);
	@Inject
	@Named("myUserSubmenu")
	private String[] myUserSubmenu;
	JSONObject jObj;

	private List<TouchMultiFieldBean> submenuItems = new ArrayList();
	@PostConstruct
	protected void sayHello() throws JSONException {
        log.info("UserSubmenu**********" + myUserSubmenu.toString());
        if (myUserSubmenu != null) {
        	for (int i = 0; i < myUserSubmenu.length; i++) {
        	 
        	jObj = new JSONObject(myUserSubmenu[i]);
        	TouchMultiFieldBean menuItem = new TouchMultiFieldBean();
        	 
        	String title = jObj.getString("title");
        	String path = jObj.getString("link");
        	String flag = jObj.getString("flag");
        	 
        	log.info("UserSubmenu**********" + title + " " + path + " " + flag);
        	menuItem.setTitle(title);
        	menuItem.setLink(path);
        	menuItem.setFlag(flag);
        	submenuItems.add(menuItem);
        	}
        	}
	}
	public List<TouchMultiFieldBean> getSubmenuItems() {
		return submenuItems;
	}
	
}
