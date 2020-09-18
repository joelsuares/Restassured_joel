package rest.assured;

import Files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(payload.CoursePrice());

		// print number of course returned by API

		int Count = js.getInt("courses.size()");
		System.out.println(Count);

		// print purchase amount
		int totalamount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalamount);

		// title of 1st course

		String Course1sttitle = js.get("courses[0].title");
		System.out.println(Course1sttitle);

		//Print All course titles and their respective Prices
		
		for(int i=0; i<Count; i++) {
			
			String CourseTitle = js.getString("courses["+i+"].title");
			int CoursePrice = js.getInt("courses["+i+"].price");
			System.out.println(CourseTitle);
			System.out.println(CoursePrice);
			
			
		}
	}

}
