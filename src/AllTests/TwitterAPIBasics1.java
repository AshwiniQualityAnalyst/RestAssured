package AllTests;
import org.testng.annotations.Test;
import files.ReusableMethods;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TwitterAPIBasics1 {

	// It's a OAuth authentication
	// Parameters u need to send to oauth
	String ConsumerKey = "";
	String ConsumerSecrets = "";
	String Token = "";
	String TokenSecret = "";
	String PrintId;

	@Test
	public void GetLatestTweet() {

		RestAssured.baseURI = "twitter";

		Response response = given().auth().oauth(ConsumerKey, ConsumerSecrets, Token, TokenSecret)
				.queryParam("Add", "Params").when().get("resources").then().extract().response();

		String responseextract = response.asString();
		System.out.println(responseextract);
		// We have reusable methods for this
		JsonPath js = new JsonPath(responseextract);
		String PrintTweet = js.get("text");
		System.out.println(PrintTweet);
		String PrintId = js.get("id");
		System.out.println(PrintId.toString()); //Long datatype error 
	}

	@Test
	public void CreateTweet() {

		RestAssured.baseURI = "twitter";

		Response response = given().auth().oauth(ConsumerKey, ConsumerSecrets, Token, TokenSecret)
				.queryParam("Add", "Params").when().post("resources").then().extract().response();

		String responseextract = response.asString();
		System.out.println(responseextract);
		// We have reusable methods for this
		JsonPath js = new JsonPath(responseextract);
		String PrintTweet = js.get("text");
		System.out.println(PrintTweet);
		PrintId = js.get("id");
		System.out.println(PrintId);
	}

	@Test
	public void DeleteTweetE2E() {

		CreateTweet();
		RestAssured.baseURI = "twitter";

		Response response = given().auth().oauth(ConsumerKey, ConsumerSecrets, Token, TokenSecret).when()
				.post("/resources/" + PrintId + "").then().extract().response();

		String responseextract = response.asString();
		System.out.println(responseextract);
		// We have reusable methods for this
		JsonPath js = new JsonPath(responseextract);
		String PrintText = js.get("text");
		System.out.println(PrintText);
		String Truncated = js.getString("truncated");
		System.out.println(Truncated);
	}
}
