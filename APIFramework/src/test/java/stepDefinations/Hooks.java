package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlaceScenario")
	
	public void beforeScenario() throws IOException
	{
		stepDefination m= new stepDefination();
		
		if(stepDefination.placeId==null)
		{
			
		m.add_place_payload_with("Kumar", "Odia", "Berhampur");
		m.user_calls_with_post_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Kumar", "GetPlaceAPI");
		}
		
	}

}
