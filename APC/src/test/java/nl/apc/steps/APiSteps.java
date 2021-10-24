package nl.apc.steps;

import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import nl.apc.managers.FileReaderManager;
import nl.apc.utils.APi;
import nl.apc.utils.Helper;
import nl.apc.utils.IConstants;

public class APiSteps {

	RequestSpecification request;
	Response response;
	Response postResponse;
	int actualStatusCode;
	JSONParser parse = new JSONParser();
	ArrayList<Object> ids = new ArrayList<Object>();
	JSONObject requestParams;
	JSONArray jsonArray;
	String contentType;

	@SuppressWarnings("unchecked")
	@Given("I will post using {string} with {string} {string} {string} to vote")
	public void i_will_post_using_with_to_vote(String EndPoint, String ImageID, String SubID, String Value) {
		request = APi.getRequest(
				FileReaderManager.getInstance().getConfigReader(IConstants.CONFIG).getProperty("x-api-key"));
		requestParams = new JSONObject();
		requestParams.put("image_id", ImageID);
		requestParams.put("sub_id", SubID);
		requestParams.put("value", Value);

		request.body(requestParams.toJSONString());

		response = request.post(EndPoint);
	}

	@Then("I should get the success {string}")
	public void i_should_get_the_success(String successMessage) {
		String successCode = response.jsonPath().get("message");
		int userID = response.jsonPath().get("id");
		Assert.assertEquals(successCode, successMessage, "Failure: Failed to vote for the user. " + userID);
	}

	@Given("{string} responce using end point {string}")
	public void responce_using_end_point(String restMethod, String endPoint) {
		request = APi.getRequest();
		if (restMethod.equalsIgnoreCase("get")) {
			response = request.get(endPoint);
		} else if (restMethod.equalsIgnoreCase("post")) {
			response = request.post(endPoint);
		}
	}

	@Then("I validate the Status code for {string}")
	public void i_validate_the_status_code_for(String expectedStatusCode) {
		actualStatusCode = response.getStatusCode();
		Assert.assertEquals(actualStatusCode, Integer.parseInt(expectedStatusCode), "Failure: Actual Status Code -> "
				+ actualStatusCode + " is not matching the expected status code -> " + expectedStatusCode);
	}

	@Given("I will get response using {string} with {string} {string}")
	public void i_will_get_response_using_with(String EndPoint, String CX, String SearchKeyword) {
		request = APi.getRequests();

		request.queryParam("key",
				FileReaderManager.getInstance().getConfigReader(IConstants.CONFIG).getProperty("api_key"));
		request.queryParam("cx", CX);
		request.queryParam("q", SearchKeyword);

		response = request.get(EndPoint);
	}

	@Then("I validate the content type {string}")
	public void i_validate_the_content_type(String expectedContentType) {
		JsonPath jsonPathEvaluator = response.jsonPath();

		Map<Object, Object> jsonMap = jsonPathEvaluator.getMap("url");
		contentType = (String) jsonMap.get("type");

		Assert.assertEquals(contentType, expectedContentType, "Failure: Content type is not matching. ");
		Helper.print("Response content type is: " + contentType);
	}

	@Then("{string} should be in the response")
	public void should_be_in_the_response(String expectedSearchedKeyword) {
		JsonPath jsonPathEvaluator = response.jsonPath();

		Map<Object, Object> jsonMap = jsonPathEvaluator.getMap("queries");

		@SuppressWarnings("unchecked")
		ArrayList<String> dataValues = (ArrayList<String>) jsonMap.get("request");
		
		Assert.assertTrue(dataValues.toString().contains(expectedSearchedKeyword),
				"Failure: Customer serached keyword is not present in the response");
		Helper.print("Customer searched keyword is present in the Response. ");
	}

	@Then("I validate the error {string}")
	public void i_validate_the_error(String expectedErrorMessage) {
		JsonPath jsonPathEvaluator = response.jsonPath();
		Map<Object, Object> jsonMap = jsonPathEvaluator.getMap("error");
		
		String actualErrorMessage = (String) jsonMap.get("message");
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Failure: Error message is not displaying when I used invalud CX value. ");
		Helper.print("I am getting the error message when I used Invalid CX value: ");
	}
	
	@Then("I validate the {string}")
	public void i_validate_the_status(String expectedStatus) {
		JsonPath jsonPathEvaluator = response.jsonPath();
		Map<Object, Object> jsonMap = jsonPathEvaluator.getMap("error");
		
		String actualStatusMessage = (String) jsonMap.get("status");
		Assert.assertEquals(actualStatusMessage, expectedStatus, "Failure: NOT_FOUND message is not displaying when I used invalud CX value. ");
		Helper.print("I am getting the Status message as NOT_FOUND when I used Invalid CX value: ");

	}
}

