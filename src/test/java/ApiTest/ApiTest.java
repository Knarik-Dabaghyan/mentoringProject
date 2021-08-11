package ApiTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ApiTest {
    private Response response;

    @BeforeMethod
    public void beforeRequest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        response = RestAssured
                .get("/users")
                .andReturn();
    }

    @Test
    public void checkStatusCode() {
        assertEquals(response.getStatusCode(), 200, "status code does not match");
    }

    @Test
    public void checkResponseHeader() {
        assertEquals(response.getContentType(), "application/json; charset=utf-8", "content tipe does not match ");
    }

    @Test
    public void checkResponseBody() {
        Object [] users=response.getBody().as(Object[].class);
        assertEquals(users.length,10,"response body is not array of 10 users");
    }
}
