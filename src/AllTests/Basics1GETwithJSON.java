package AllTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import files.ReusableMethods;

public class Basics1GETwithJSON {

	@Test
	public void Get() 
	{
		// BaseURL or Host
		RestAssured.baseURI = "https://maps.googleapis.com";

		Response res = given().log().all(). //Logging
				param("location", "-33.8670522,151.1957362").
				param("radius", "1500").
				param("key", "AIzaSyA8qEnDdn1sqCISZDDtbFln08aY_cpHcKg").
				when().
				get("/maps/api/place/nearbysearch/json").
				then().assertThat().
				statusCode(200).
				and().
				contentType(ContentType.JSON).
				and().
				body("results[0].name", equalTo("Sydney")).  
				and().
				body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).
				and().header("Server", "scaffolding on HTTPServer2").
				extract().
				response();

		JsonPath js = ReusableMethods.rawToJson(res);
		int count = js.get("results.size()");
		for(int i=0; i<count; i++)
		{
			String AllName = js.get("results["+i+"].name");
			System.out.println(AllName);
		}

	}

}
