package ResponseStatusCode;

import org.testng.Assert;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class CheckForValidResponse {
	
	@Test(priority=1)
	public void GetSingleUser() {
		
		baseURI = "https://reqres.in/api/users/2";
		
		RequestSpecification requestSpeci = given();
		
		Response response =  requestSpeci.get();
		
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(statusCode,200,"Incorrect Status Code Received");
		
		String statusLine = response.getStatusLine();
		
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK","Incorrect Status Line Returend");
	}
	
	
	
	@Test(priority=2)
	public void GetSingleUserUsingValidatableResponse() {
		baseURI = "https://reqres.in/api/users/19";
		
		RequestSpecification requestSpeci = given();
		
		Response response =  requestSpeci.get();
		
		ValidatableResponse validateRes = response.then();
		
		validateRes.statusCode(200);
		
		System.out.println("Second Validation");
		
		validateRes.statusLine("HTTP/1.1 200 OK");
	}
	
	
	
	@Test(priority=3)
	public void GetSingleUserBDD_Style() {
		given()
		
		.when()
		
		.get("https://reqres.in/api/users/2")
		
		.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK");
		
	}
}