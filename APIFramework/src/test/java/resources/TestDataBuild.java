package resources;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload() {

		AddPlace g = new AddPlace();
		g.setAccuracy(50);
		g.setAddress("Berhampur");
		g.setLanguage("Odia");
		g.setName("subhrant");
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

}
