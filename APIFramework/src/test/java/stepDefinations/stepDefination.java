package stepDefinations;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.AddPlace;
import static org.junit.Assert.*;

public class stepDefination {
	RequestSpecification resp;
	Response response;
	ResponseSpecification res;

	@Given("Add Place Payload")
	public void add_place_payload(){

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		AddPlace g = new AddPlace();
		g.setAccuracy(50);
		g.setAddress("Berhampur");
		g.setLanguage("Odia");

		pojo.Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		g.setLocation(l);

		g.setName("subhrant");
		g.setPhone_number("+91 9937441761");

		List<String> mylist = new ArrayList<String>();
		mylist.add("Xyz");
		mylist.add("acc");
		g.setTypes(mylist);

		g.setWebsite("Google maps");

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		resp = given().spec(req).body(g);
		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();


	}

	@When("user calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) {

		response = resp.when().post("/maps/api/place/add/json").then().log().all().spec(res).extract().response();

	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {

		assertEquals(200, response.getStatusCode());

	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		String respon1 = response.asString();
		JsonPath js = new JsonPath(respon1);
		assertEquals(js.get(keyValue).toString(), expectedValue);

	}

}
