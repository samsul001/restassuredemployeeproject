//TestName: Create new record in databse
package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC003_Post_Employee_Record extends TestBase{
	
	String empName = RestUtils.empName();
	String empSal = RestUtils.empSal();
	String empAge = RestUtils.empAge();
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	void createEmployee() throws InterruptedException {
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary",empSal);
		requestParams.put("age", empAge);
		
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.request(Method.POST,"/create");
		Thread.sleep(3000);
	}
	
	@Test
	void checkResponseBody() {
		
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSal), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
		
	}
	
	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkStatusline() {
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType() {
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json");
	}
	
	@Test
	void checkServerType() {
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "cloudflare");
	}
	
	@Test
	void checkContentEncoding() {
		String contentEncoding =response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");
	}
}
