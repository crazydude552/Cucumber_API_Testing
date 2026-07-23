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
import static io.restassured.RestAssured.requestSpecification;

import org.junit.jupiter.api.Assertions;
import utils.ApiClient;

public class TestSteps {

    private final ApiClient api = new ApiClient();

    private Response response;
    @Given("the user connect to the rest endpoint {string}")
    public void the_endpoint(String ep) {
        RestAssured.baseURI = ep;

    }


    @When("Post the valid credentials")
    public void postTheValidCredentials(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String email = rows.get(0).get("email");
        String password = rows.get(0).get("password");

        // Manually format the JSON string to bypass the serializer requirement
        String jsonBody = String.format("{\"user\":{\"email\":\"%s\",\"password\":\"%s\"}}", email, password);

        response=api.post("api/users/login",jsonBody);



        // 3. Asserting Specific JSON Values using JsonPath
        JsonPath jsonPath = response.jsonPath();
        String actualEmail = jsonPath.getString("user.email");
        String actualToken = jsonPath.getString("user.token");
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
    public void postTheInvalidCredentials(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String email = rows.get(0).get("email");
        String password = rows.get(0).get("password");

        // Manually format the JSON string to bypass the serializer requirement
        String jsonBody = String.format("{\"user\":{\"email\":\"%s\",\"password\":\"%s\"}}", email, password);

        response=api.post("api/users/login",jsonBody);
    }

    @Then("the user unauthorized with response code {int}")
    public void theUserUnauthorizedWithResponseCode(int arg0) {
        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(actualStatusCode,arg0);
    }


}
