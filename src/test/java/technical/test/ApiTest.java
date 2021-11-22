package technical.test;


import org.json.JSONObject;

// import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.testng.Assert;
// import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
// import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

// import technical.test.APIEndpoints;


public class ApiTest 
{
    String Email = null;
    String UserID = null;
    String UserJob = null;

    @Test
    public void getUserList(){
        RestAssured.baseURI = (APIEndpoints.baseURL);
        RequestSpecification httpRequest = RestAssured.given();

        Response getUserlistResponse = httpRequest.queryParam("page","2").get(APIEndpoints.users);

        JsonPath jsonPath = getUserlistResponse.jsonPath();
        Email = jsonPath.get(JsonPaths.userEmail);

        int responseCode = getUserlistResponse.getStatusCode();
        Assert.assertEquals(responseCode, 200);
        System.out.println("Response Code is " + responseCode);

        String responseBody = getUserlistResponse.getBody().asPrettyString();
        Assert.assertEquals(Email, "lindsay.ferguson@reqres.in");
        System.out.println("Response Body is => " + responseBody);
    }

    @Test
    public void postCreateUser(){
        RestAssured.baseURI = (APIEndpoints.baseURL);
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "marco");
        requestBody.put("job", "senior QA");

        httpRequest.body(requestBody.toString());
        Response postCreateUserResponse = httpRequest.post(APIEndpoints.users);

        JsonPath jsonPath = postCreateUserResponse.jsonPath();
        UserID = jsonPath.get(JsonPaths.userName);
        UserJob = jsonPath.get(JsonPaths.userJob);

        int responseCode = postCreateUserResponse.getStatusCode();
        Assert.assertEquals(responseCode, 201);
        System.out.println("Response Code is " + responseCode);
        System.out.println("User ID is " + UserID);
        System.out.println("User Job is " + UserJob);

        String responseBody = postCreateUserResponse.getBody().asPrettyString();
        System.out.println("Response Body is => " + responseBody);
    }
}