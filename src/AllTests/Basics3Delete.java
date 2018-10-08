package AllTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import files.resources;
import files.payload;

public class Basics3Delete {
	Properties prop;

	@BeforeTest
	public void getData() throws IOException{
		prop = new Properties();
		FileInputStream fis = new FileInputStream("H:\\RestAssured\\src\\files\\env.properties");
		prop.load(fis);
		//prop.getProperty("HOST");
	}

	@Test
	public void POSTandDELETE(){
		//Check Basics2POST.java
		/*
		String POSTpayload = "{"+
				"\"location\":{"+
				"\"lat\" : -38.383494,"+
				"\"lng\" : 33.427362"+
				"},"+
				"\"accuracy\":50,"+
				"\"name\":\"Frontline house\","+
				"\"phone_number\":\"(+91) 983 893 3937\","+
				"\"address\" : \"29, side layout, cohen 09\","+
				"\"types\": [\"shoe park\",\"shop\"],"+
				"\"website\" : \"http://google.com/\","+
				"\"language\" : \"French-IN\""+
				"}" ;

		 */

		//Task 1 Grab the response
		//RestAssured.baseURI = "http://216.10.245.166";
		RestAssured.baseURI = prop.getProperty("HOST");
		//Use of extract
		Response response = given().
				queryParam("key", prop.getProperty("KEY")).
				body(payload.POSTpayloadData()).    
				when().
				post(resources.placePostData()).
				then().
				assertThat().
				statusCode(200).
				and().
				contentType(ContentType.JSON).
				and().
				body("status", equalTo("OK")).
				extract().response();

		String responseextract = response.asString();
		System.out.println(responseextract); //Raw format to String format to now JSON

		//Task 2 Grab the Place ID from response
		//Convert String into JSON

		JsonPath js = new JsonPath(responseextract);
		String placeid = js.get("place_id");
		//Task 2 is done
		System.out.println("PlaceID: "+placeid);

		String DELETEpayload = "{"+
				"\"place_id\":\""+placeid+"\""+       
				"}";

		//Task 3 place this Placeid in the Delete request

		given().
		queryParam("key", "qaclick123").
		body(DELETEpayload).
		when().
		post("/maps/api/place/delete/json").
		then().
		assertThat().
		statusCode(200).
		and().
		body("status", equalTo("OK"));
	}
}
