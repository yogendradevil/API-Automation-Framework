package com.spree.util;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestResponseUtil {
	 //Verify the http response status returned. Check Status Code is 200?
    public static void checkStatusIs200(Response res) {
        Assert.assertEquals(res.getStatusCode(), 200, "Status Check Passed!");
    }
    
    //Verify the http response status returned. Check Status Code is 200?
    public static void checkStatusIs204(Response res) {
        Assert.assertEquals(res.getStatusCode(), 301, "Status Check Re-Directed!");
    }
    
    public static void checkStatusIs422(Response res) {
        Assert.assertEquals(res.getStatusCode(), 422, "Status Check Failed!");
    }
    
    //check if expected value in response body is correct
    public static void checkValue(Response response, String path, String expVal) {
    	JsonPath jsonPathEvaluator = response.getBody().jsonPath();
    	String actVal = jsonPathEvaluator.get(path).toString();
    	Assert.assertEquals(actVal, expVal);
    }
    
    public static String getValue(Response response, String path) {
    	JsonPath jsonPathEvaluator = response.getBody().jsonPath();
    	String result = jsonPathEvaluator.get(path).toString();
    	return result;
    }
}
