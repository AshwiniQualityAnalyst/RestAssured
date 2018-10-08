import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import files.ReusableMethods;

public class Basics2POSTwithXML {

	@Test
	public void POST() throws IOException
	{
		String POSTDATA = GenerateStringFromResource("H:\\RestAssured\\src\\files\\PostData.xml");
		RestAssured.baseURI = "http://216.10.245.166";
		Response response = given().
				queryParam("key", "qaclick123").
				//Very important convention
				body(POSTDATA).    //Very important convention
				when().
				post("/maps/api/place/add/xml").
				then().
				assertThat().
				statusCode(200).
				and().
				contentType(ContentType.XML).
				extract().
				response();

		//Convert raw data into String
		XmlPath XP = ReusableMethods.rawToXML(response);
		String Placeid = XP.getString("response.place_id");
		System.out.println("Placeid: "+Placeid);
		/*
		String responseextract = response.asString();
		System.out.println(responseextract);
		 */
		//Now convert response to XML format

		/*
		XmlPath XP = new XmlPath(responseextract);
		String Placeid = XP.getString("response.place_id");
		System.out.println("PLACEID: "+Placeid);
		 */

		//Create a place = response (place = id)
		//and delete Place = (Request - Place id)
	}

	//Writing method to convert XML in to String
	public static String GenerateStringFromResource(String path) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	//IMP LINK: https://static.javadoc.io/com.jayway.restassured/rest-assured/1.4/com/jayway/restassured/path/xml/XmlPath.html
}
