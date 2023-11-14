package resources;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {

		AddPlace g = new AddPlace();
		g.setAccuracy(50);
		g.setAddress(address);
		g.setLanguage(language);
		g.setName(name);
		g.setPhone_number("+91 9937441761");

		List<String> mylist = new ArrayList<String>();
		mylist.add("Xyz");
		mylist.add("acc");
		g.setTypes(mylist);

		g.setWebsite("Google maps");
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		g.setLocation(l);
		return g;
	}
	public String deletePlacePayload(String placeId) {
		return "{\\r\\n    \\\"place_id\\\":\\\""+placeId+"\\\"\\r\\n}\\r\\n";
		
	}
	
	

}
