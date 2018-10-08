package files;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {

	public static XmlPath rawToXML(Response r)
	{
		String response = r.asString();
		XmlPath x = new XmlPath(response);
		return x;
	}

	public static JsonPath rawToJson(Response r)
	{
		String response = r.asString();
		JsonPath x = new JsonPath(response);
		return x;
	}

	//JIRA API---Step 1
	public static String getSessionKEY()
	{
		RestAssured.baseURI = "http://localhost:8080";
		Response response = given().header("Content-Type", "application/json").
				body("{ \"username\": \"asharma88.qa\", \"password\": \"Neha1988\" }").
				when().
				post("/rest/auth/1/session").
				then().
				statusCode(200).
				extract().
				response();

		JsonPath js = ReusableMethods.rawToJson(response);
		String SessionValue = js.get("session.value");
		System.out.println("SessionValue: "+SessionValue);
		return SessionValue;
	}
}
