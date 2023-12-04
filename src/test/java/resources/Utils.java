package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;
	static String resp;

	public RequestSpecification GenericRequestSpecifications() throws Exception {

		// the line no 24 code is used when user needs to pass multiple test data.
		if(req == null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		 req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL")).addQueryParam("key", "qaclick123")
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		return req;
	}
		return req;
	}
	
	public String getGlobalValue(String key) throws Exception {
		Properties p = new Properties();
		FileInputStream fi = new FileInputStream("C:\\Amol\\LunaWorkSpace\\E2EAPIFrameworkCucumber\\global.properties");
		p.load(fi);
		return p.getProperty(key);
	}
	
	public String getJsonPath(Response response , String key) {
		String resp = response.asString();
		System.out.println(resp);
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}

}
