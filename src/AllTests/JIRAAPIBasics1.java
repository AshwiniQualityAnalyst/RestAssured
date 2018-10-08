package AllTests;
import org.testng.annotations.Test;

import files.ReusableMethods;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class JIRAAPIBasics1 {
	//POST USAGE
	//JIRA API---Step 2
	//Create a bug
	//Each operation will have COOKIE as a header in JIRA API TESTING
	@Test
	public void JiraAPICreateBug(){
		RestAssured.baseURI = "http://localhost:8080";
		Response response = given().
				header("Content-Type", "application/json").
				header("cookie", "JSESSIONID="+ReusableMethods.getSessionKEY()+"").
				body("{"+
						"\"fields\": {"+
						"\"project\":"+ 
						"{"+
						"\"key\": \"RES\""+
						"},"+
						"\"summary\": \"MasterCardGOLD Defect\","+
						"\"description\": \"Creating using REST API\","+
						"\"issuetype\": {"+
						"\"name\": \"Bug\""+
						"}"+
						"}"+
						"}").when().
				post("/rest/api/2/issue").
				then().
				statusCode(201).
				extract().
				response();
		
		JsonPath js = ReusableMethods.rawToJson(response);
		String id = js.get("id");
		System.out.println("Bug ID: "+id);




	}
}
