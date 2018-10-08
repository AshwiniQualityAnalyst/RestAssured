
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

public class Basics1GET {

	@Test
	public void Get() 
	{
		// BaseURL or Host
		RestAssured.baseURI = "https://maps.googleapis.com";

		given().
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
		body("results[0].name", equalTo("Sydney")).  //This will pass if you want to fail it mentioned Sydney1
		and().
		body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).
		and().header("Server", "scaffolding on HTTPServer2");
		//body("results[0].geometry.location.lat", equalTo("-33.8688197"));  --it will fail because lat varies
	}

}
