//TestName:Get a single employee from rest APIs
package com.employeeapi.testCases;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_Get_A_Single_Employee_Data extends TestBase {
	@BeforeClass
	void getAllEmplyees() throws InterruptedException {
		//logger.info("******Started TC001_Get_Employees******");

		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employee/1");
		Thread.sleep(3000);
	}

	@Test
	void checkResponseBody() {
		//logger.info("******Checking response body******");

		String responseBody = response.getBody().asString();
		//logger.info("responseBody==> "+responseBody);
		Assert.assertEquals(responseBody.contains(empID), true);
	}

	@Test
	void checkStatusCode() {
		//logger.info("******Checking status Code******");

		int statusCode = response.getStatusCode();
		//logger.info("Status Code is ==> "+statusCode);
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	void checkStatusLine() {
		//logger.info("******Checking status Code******");

		String statusLine = response.getStatusLine();
		//logger.info("Status Code is ==> "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	//@Test
	void checkResponseTime() {
		//logger.info("******Checking response time******");

		long responseTime = response.getTime();
		//logger.info("rsponse time is ==> " +responseTime);

		//if(responseTime>2000)
		//logger.warn("response time is greater than 2000");
		//System.out.println(responseTime);
		Assert.assertTrue(responseTime<2500);

	}

	@Test
	void checkContentType() {
		//logger.info("******Checking Content-Type******");

		String contentType = response.header("Content-Type");
		//logger.info("Content-Type is==> "+contentType);
		Assert.assertEquals(contentType, "application/json");
	}

	@Test
	String checkContentEncoding(){
		//logger.info("******Checking Content-Encoding******");

		String contentEncoding = response.header("Content-Encoding");
		//logger.info("Content Encoding is ==> " +contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		return contentEncoding;
	}

	@Test
	void checkServerType() {
		//logger.info("******Checking Server-Type******");

		String serverType = response.header("Server");
		//logger.info("Server type is ==> "+serverType);
		Assert.assertEquals(serverType, "cloudflare");

	}

	//@Test
	void checkContentLength() {
		//logger.info("******Checking Content-Length******");

		String contentLength = response.header("Content-Length");
		//logger.info("Content-Length ==> "+contentLength);

		Assert.assertTrue(Integer.parseInt(contentLength)<800);

	}

	@AfterClass
	void tearDown() {
		//logger.info("*****Finished TC001_Get_Employees******");
		//String testresults="*****Finished TC001_Get_Employees******";

	}

}
