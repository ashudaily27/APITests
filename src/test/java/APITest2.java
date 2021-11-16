import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.runner.Request;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest2 {

    @Test
    void successregister(){
        RequestSpecification request = given();
        request.header("Content-type", "application/json");

        JSONObject json = new JSONObject();
        json.put("email","eve.holt@reqres.in");
        json.put("password", "pistol");

        request.body(json.toJSONString());

        Response response = request.post("https://reqres.in/api/register");
        int code=response.getStatusCode();
        Assert.assertEquals(code,200);
    }

    @Test
    void unsuccessregister(){
        RequestSpecification request = given();
        request.header("Content-type", "application/json");

        JSONObject json = new JSONObject();
        json.put("email", "sydney@fife");

        request.body(json.toJSONString());

        Response response = request.post("https://reqres.in/api/register");
        int code=response.getStatusCode();
        Assert.assertEquals(code,400,"test is fine");
    }

    @Test
    void successlogin(){
        RequestSpecification request = given();
        request.header("Content-type", "application/json");

        JSONObject json = new JSONObject();
        json.put("email", "eve.holt@reqres.in");
        json.put("password", "cystic");

        request.body(json.toJSONString());

        Response response = request.post("https://reqres.in/api/login");
        int code=response.getStatusCode();
        Assert.assertEquals(code,200);
    }

    @Test
    void unsuccesslogin(){
        RequestSpecification request = given();
        request.header("Content-type", "application/json");

        JSONObject json = new JSONObject();
        json.put("email", "peter@klaven");

        request.body(json.toJSONString());

        Response response = request.post("https://reqres.in/api/login");
        int code=response.getStatusCode();
        Assert.assertEquals(code,400);
    }

    //@Test
//    void test5(){
////        given().get("https://reqres.in/api/users?page=2").then().statusCode(200).
////                body("data[4].first_name",equalTo("George"));
//
//                Response response=get("https://reqres.in/api/users?page=2");
//
//
//    }
}
