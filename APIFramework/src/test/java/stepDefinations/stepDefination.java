package stepDefinations;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import static org.junit.Assert.*;
import java.io.IOException;

public class stepDefination extends Utils {
	RequestSpecification resp;
	Response response;
	ResponseSpecification res;
	static String placeId;
	
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException
	{
		resp = given().spec(reuestSpecification()).body(data.addPlacePayload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
		
		APIResources resourceAPI=APIResources.valueOf(resource);
        res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        
        if(method.equalsIgnoreCase("POST"))
		response = resp.when().post(resourceAPI.getResource());
        else if(method.equalsIgnoreCase("GET"))
    		response = resp.when().get(resourceAPI.getResource());
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {

		assertEquals(200, response.getStatusCode());

	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		
		assertEquals(getJsonPath(response, keyValue), expectedValue);

	}
	

@Then("verify place_Id created maps to {string} using {string}")
public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
 
	//request spec
	placeId=getJsonPath(response, "place_id");
	resp = given().spec(reuestSpecification()).queryParam("place_id", placeId); 
	user_calls_with_post_http_request(resource, "GET");
	assertEquals(getJsonPath(response, "name"),expectedName );
}



@Given("DeletePlace Payload")
public void delete_place_payload() throws IOException {
 resp=  given().spec(reuestSpecification()).body(data.deletePlacePayload(placeId));
}

	

}
