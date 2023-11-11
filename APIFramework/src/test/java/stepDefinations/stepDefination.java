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
import resources.TestDataBuild;
import resources.Utils;
import static org.junit.Assert.*;

import java.io.IOException;

public class stepDefination extends Utils {
	RequestSpecification resp;
	Response response;
	ResponseSpecification res;
	
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload")
	public void add_place_payload() throws IOException {
		resp = given().spec(reuestSpecification()).body(data.addPlacePayload());
		
	}

	@When("user calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) {
		
        res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response = resp.when().post("/maps/api/place/add/json").then().spec(res).extract().response();
		
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
