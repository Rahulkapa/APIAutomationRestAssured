package CURD;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.error.AssertJMultipleFailuresError;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;

import Actions.AssertActions;
import Base.TestBase;
import EndPoints.APIConstants;
import Modules.PayloadManager;
import Payloads.gson.BookingRespons;
import Utils.YAMLReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class TC_CreateBooking extends TestBase{
	
	@Owner("Rahul")
	@Severity(SeverityLevel.CRITICAL)
	@Description("TC#1 verify that Create Booking works and ID is Generated !")
	
	@Test (groups = {"stage","P0"})
	public void testCreateBooking() throws JsonProcessingException {
		// call the request
		// call the payload
		// call the assertion block
		payloadManager = new PayloadManager();
		actions = new AssertActions();
		requestSpecification  = RestAssured.given()
				                .baseUri(APIConstants.Base_URL)
				                .contentType(ContentType.JSON);
		
		// 1. Call the request
//		requestSpecification.basePath(APIConstants.CREATE_BOOKING);
//		response = requestSpecification.when().body(payloadManager.createPayload()).post();
//	    validatableResponse = response.then().log().all();
//	    validatableResponse.statusCode(200);
	    
	    // get the booking Id by using JsonPath - if we want only value on payload then we use jsonpath
	    
	    jsonPath = JsonPath.from(response.asString());
	    String bookingID = jsonPath.getString("bookingid");
	    
	    // But we want more than value needed than we use serilzation and deserialization concept.
	    // object to string and string to object
	    
	    BookingRespons bookingRespons = payloadManager.JsonToObject(response.asString());
	    
	    
	    // Step 3 - call the assertion block.
	    
	    assertThat(bookingRespons.getBookingid().toString()).isNotNull().isNotEmpty();
		
		
	}
	
	@Description("TC#2 verify that Create Booking with no payload !")
	@Test (groups = {"stage","P0"})
	public void testCreateBooking_negative() throws JsonProcessingException {
		
		requestSpecification.basePath(APIConstants.CREATE_BOOKING);
		response = requestSpecification.when().body("").post();
	    validatableResponse = response.then().log().all();
	    validatableResponse.statusCode(500);
	      
	    jsonPath = JsonPath.from(response.asString());
	    String bookingID = jsonPath.getString("bookingid");
	    
	    
	    BookingRespons bookingRespons = payloadManager.JsonToObject(response.asString());
	    
	    assertThat(bookingRespons.getBookingid().toString()).isNotNull().isNotEmpty();
	    
	    //new YAMLReader().readKey().get("Username");

}
}