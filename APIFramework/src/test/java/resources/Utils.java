package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	RequestSpecification req;
	
	public RequestSpecification reuestSpecification() throws IOException {
		
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		
	req= new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")) //give the key name and it will get the value for the same
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))				
				.setContentType(ContentType.JSON).build();
		return req;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Subhrant\\git\\ApiFramework\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);	
		return	prop.getProperty(key);	
		
		
	}
}
