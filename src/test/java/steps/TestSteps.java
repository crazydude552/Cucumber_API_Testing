package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Assertions;

public class TestSteps {

    public Response response;
    @Given("the user connect to the rest endpoint {string}")
    public void the_endpoint(String ep) {
        System.out.println(ep+"\n");
        RestAssured.baseURI = ep;

    }


    @When("Post the valid credentials")
    public void postTheValidCredentials(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String email = rows.get(0).get("email");
        String password = rows.get(0).get("password");

        // Manually format the JSON string to bypass the serializer requirement
        String jsonBody = String.format("{\"user\":{\"email\":\"%s\",\"password\":\"%s\"}}", email, password);

        response = given()
                .contentType(ContentType.JSON)
                .body(jsonBody) // Passing a raw String works out-of-the-box
                .when()
                .post("api/users/login")
                .then()
                .extract().response();


        // 3. Asserting Specific JSON Values using JsonPath
        JsonPath jsonPath = response.jsonPath();
        String actualEmail = jsonPath.getString("user.email");
        String actualToken = jsonPath.getString("user.token");
        System.out.println(actualEmail);
        Assertions.assertEquals("pvmrmoorthy@gmail.com", actualEmail, "Email in response match payload");
        Assertions.assertNotNull(actualToken, "Authorization token was missing from response");
    }

    @Then("the user get valid response code {int}")
    public void theUserGetValidResponseCode(int expectedStatusCode) {
            int actualStatusCode = response.getStatusCode();
            Assertions.assertEquals(actualStatusCode,expectedStatusCode);
    }

    @Given("the user connect to the rest endpoint https:\\/\\/demo.realworld.show\\/")
    public void theUserConnectToTheRestEndpointHttpsDemoRealworldShow() {
    }

    @When("Post the invalid credentials")
    public void postTheInvalidCredentials() {
    }

    @Then("the user unauthorized with response code {int}")
    public void theUserUnauthorizedWithResponseCode(int arg0) {
    }


}
