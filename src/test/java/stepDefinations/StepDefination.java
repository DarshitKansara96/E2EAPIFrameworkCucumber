package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import pojo.GetAddPlaceSerialization;
import pojo.LocationSerialize;
import resources.APIResourcesEnum;
import resources.TestDataBuilder;
import resources.Utils;

public class StepDefination extends Utils {

	static RequestSpecification res;
	ResponseSpecification respec;
	Response response;
	TestDataBuilder td = new TestDataBuilder();
	static String place_id;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws Exception {
		 res=given().spec(GenericRequestSpecifications())
					.body(td.AddPlacePayload(name,language,address));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpmethod) {
		APIResourcesEnum resourceAPI = APIResourcesEnum.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		// The method name created in the Enum class should be same as it is mentioned
		// in the feature file
		// i.e AddPlaceAPI. So now when valueOf() function will execute the constructor
		// then it will execute the method
		// for this particular stepdefinition method only which is mentioned in Feature
		// file. So in this case the AddPlaceAPI value
		// will be passed as a string in this stepdefinition method.

		respec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		// response =
		// res.when().post(addplaceresource.getResource()).then().log().all().spec(respec).extract().response();

		if (httpmethod.equalsIgnoreCase("POST")) {
			response = res.when().post(resourceAPI.getResource());
		} else if (httpmethod.equalsIgnoreCase("GET")) {
			response = res.when().get(resourceAPI.getResource());
		}
	}

	@Then("The API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedvalue) {

		assertEquals(getJsonPath(response, keyvalue), expectedvalue);

		// In the above line the actualvalue and expectedvalue is nothing but the
		// regular expressions mentioned
		// in the feature file.
		// So the attributes status and scope along with their values will fall under
		// the arguments mentioned in the method.
	}

	@Then("verify place_Id created maps with {string} using {string}")
	public void verify_place_id_created_maps_with_using(String expectedname, String resource) throws Exception {

		place_id = getJsonPath(response, "place_id");
		System.out.println(place_id);
		res = given().spec(GenericRequestSpecifications()).queryParam("place_id", place_id);

		// Since in the method created on the basis of When keyword in the feature file
		// where we put an if else condition for post and get requests so we can use the
		// same method in this method and instead of Post now we will use Get.
		user_calls_with_http_request(resource, "GET");

		String actualname = getJsonPath(response, "name");
		assertEquals(actualname, expectedname);
	}

	@Given("Delete Payload")
	public void delete_payload() throws Exception {
		given().spec(GenericRequestSpecifications()).body(td.deletepayload(place_id));
	}
}
