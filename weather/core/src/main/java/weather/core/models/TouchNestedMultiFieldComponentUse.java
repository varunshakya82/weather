package weather.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import weather.core.bean.TouchNestedMultiFieldBean;

@Model(adaptables = Resource.class)
public class TouchNestedMultiFieldComponentUse {

	private static final Logger LOGGER = LoggerFactory.getLogger(TouchNestedMultiFieldComponentUse.class);
	private List<TouchNestedMultiFieldBean> submenuItems = new ArrayList();


	public List<TouchNestedMultiFieldBean> getSubmenuItems() {
		return submenuItems;
	}



	@Inject
	@Named("myUserSubmenu")
	private String[] myUserSubmenu;



	@PostConstruct
	protected void sayHello() {

		JsonObject jObj;
		JsonArray jNestedArr;
		Gson gson = new Gson();
		try {
			if (myUserSubmenu != null) {
				for (int i = 0; i < myUserSubmenu.length; i++) {
					JsonElement json = gson.fromJson(myUserSubmenu[i], JsonElement.class);
					jObj = json.getAsJsonObject();
					TouchNestedMultiFieldBean menuItem = new TouchNestedMultiFieldBean();
					String title = jObj.get("title").toString();
					String path = jObj.get("link").toString();
					LOGGER.info("*******" + title + " " + path);
					jNestedArr = jObj.getAsJsonArray("myNestedUserSubmenu");
					if (!jNestedArr.isJsonNull() && jNestedArr.size() > 0) {
						JsonObject jNestedObj = jNestedArr.get(0).getAsJsonObject();
						menuItem.setSubtitle(jNestedObj.get("subtitle").toString());
						LOGGER.info("*******" + menuItem);
					}
					menuItem.setTitle(title);
					menuItem.setLink(path);
					submenuItems.add(menuItem);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception while Multifield data {}", e.getMessage(), e);
		}
	}

}