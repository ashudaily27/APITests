import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;




public class APITests {

    //@Test
    void test1(){
        Response response = get("https://reqres.in/api/users?page=2");
        System.out.println("Response : " + response.asString());
        System.out.println("Status Code : " + response.getStatusCode());
        System.out.println("Body : " + response.getBody().asString());
        System.out.println("Time Taken : "+ response.getTime());
        System.out.println("Header : " + response.getHeader("content-type"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
    }

    //@Test
    void test2(){
        given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).body("data[1].id",equalTo(8));
    }

   // @Test
    void test3(){
        given().get("https://reqres.in/api/users?page=2").then().statusCode(200).
                body("data[4].first_name",equalTo("George")).
                body("data.first_name", hasItems("George", "Rachel"));
    }

    //@Test
    void test4(){
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        map.put("name", "Rahu");
//        map.put("job", "Teacher");

//        System.out.println(map);

        JSONObject request = new JSONObject();

        request.put("name", "Raghavan");
        request.put("job", "Teacher");

        System.out.println(request.toJSONString());

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("https://reqres.in/api/users").then().
                statusCode(201).log().all();
    }

    //@Test
    void test5(){

        JSONObject request = new JSONObject();

        request.put("name", "Raghavan");
        request.put("job", "Teacher");

        System.out.println(request.toJSONString());

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                put("https://reqres.in/api/users/2").then().
                statusCode(200).log().all();

    }
}
