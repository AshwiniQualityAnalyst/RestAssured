package AllTests;
import org.testng.annotations.Test;

import files.ReusableMethods;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JIRAAPIBasics3 {
	//Remeber we need id here
	//PUT USAGE
	//JIRA API---Step 4 
	//Updating comment in  a bug
	//Each operation will have COOKIE as a header in JIRA API TESTING

	@Test
	public void JiraAPI(){
		RestAssured.baseURI = "http://localhost:8080";
		Response response = given().
				header("Content-Type", "application/json").
				header("cookie", "JSESSIONID="+ReusableMethods.getSessionKEY()+"").
				body("{"+
						"\"body\": \"Updating comment using REST API\","+
						"\"visibility\": {"+
						"\"type\": \"role\","+
						"\"value\": \"Administrators\""+
						"}"+
						"}").

				when().
				put("/rest/api/2/issue/10003/comment/10001").
				then().
				statusCode(200).
				extract().
				response();
	}
	
	//Above example and Below are same
	@Test
	public void JiraAPIPathParams(){
		RestAssured.baseURI = "http://localhost:8080";
		Response response = given().
				header("Content-Type", "application/json").
				header("cookie", "JSESSIONID="+ReusableMethods.getSessionKEY()+"").
				pathParam("commentid", "10001").                                       //path param
				body("{"+
						"\"body\": \"Updating comment using REST API\","+
						"\"visibility\": {"+
						"\"type\": \"role\","+
						"\"value\": \"Administrators\""+
						"}"+
						"}").

				when().
				put("/rest/api/2/issue/10003/comment/{commentid}").   //use path parma
				then().
				statusCode(200).
				extract().
				response();
	}
}
