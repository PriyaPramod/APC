package nl.apc.utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import nl.apc.managers.FileReaderManager;

public class APi {

	public static RequestSpecification getRequest() {
		RestAssured.baseURI = FileReaderManager.getInstance().getConfigReader(IConstants.CONFIG).getProperty("BaseURI");
		RequestSpecification request = RestAssured.given();
		request.header("X-CMC_PRO_API_KEY",
				FileReaderManager.getInstance().getConfigReader(IConstants.CONFIG).getProperty("APIKey"));
		request.header("Content-Type", "application/json");

		return request;
	}

	public static RequestSpecification getRequest(String APIKey) {
		RestAssured.baseURI = FileReaderManager.getInstance().getConfigReader(IConstants.CONFIG).getProperty("BaseURI");
		RequestSpecification request = RestAssured.given();
		request.header("x-api-key", APIKey);
		request.header("Content-Type", "application/json");

		return request;
	}

	public static RequestSpecification getRequests() {
		RestAssured.baseURI = FileReaderManager.getInstance().getConfigReader(IConstants.CONFIG).getProperty("CustomerSerchBaseURI");;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");

		return request;
	}

}
