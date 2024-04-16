package Base;

import org.apache.http.client.methods.RequestBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;

import Actions.AssertActions;
import EndPoints.APIConstants;
import Modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public RequestSpecification requestSpecification;
	
	public AssertActions actions ;
	
	public PayloadManager payloadManager ;
	
	public JsonPath jsonPath ;
	
	public Response response ;
	
	public ValidatableResponse validatableResponse ;
	
	
	@BeforeMethod(alwaysRun = true)
	public void setup() {
		// Reset the rest Assured base URL
		// base Url
		// content type
		payloadManager = new PayloadManager();
		actions = new AssertActions();
		requestSpecification  = RestAssured.given()
				                .baseUri(APIConstants.Base_URL)
				                .contentType(ContentType.JSON);
//		requestSpecification = new RequestSpecBuilder()
//				              .setBaseUri(APIConstants.Base_URL)
//				              .addHeader("Content-Type","application/json")
//				              .build().log().all();
	}
	
	
	public String getToken() throws JacksonException {
		
		requestSpecification  = RestAssured.given()
                  .baseUri(APIConstants.Base_URL)
                  .basePath("/auth")
                  .contentType(ContentType.JSON);
			
		// Step 1 :Create a token and to get a token
			
		String payload = payloadManager.setToken();
		
	    response = requestSpecification.contentType(ContentType.JSON)
	    		.body(payload)
	    		.when().post();
		
		jsonPath = new JsonPath(response.asString());
		return jsonPath.getString("token");
		
		
		
		
	}
	

}
