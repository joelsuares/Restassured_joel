package rest.assured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.ReusableCode;
import Files.payload;

public class basic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Add place api
		// validate id the add place api working or not

		// 3 method in the rest assured used
		// 1.given -all input details
		// 2. when - submit the api -- resource and method
		// 3. then - validate the responce
 
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().log().all().queryParam("key ", "qaclick123")
				.header("Content-Type", "application/json").body(payload.Addplace()).when()
				.post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

		System.out.println(response);
		JsonPath js = new JsonPath(response); // to parsing json

		String placeId = js.getString("place_id");
		System.out.println(placeId);

		// update place

		String newAddress = "btm 2nd stage";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeId + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// get place

		String ResponsegetPlace = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();

		JsonPath js1 = ReusableCode.rawtoJson(ResponsegetPlace);
		String ActualAddress = js1.getString("address");

		System.out.println(ActualAddress);

		Assert.assertEquals(ActualAddress, newAddress);

	}

}
