package steps;

import io.cucumber.java.en.*;

public class TestSteps {
    @Given("the user connect to the rest endpoint {string}")
    public void the_endpoint(String ep) {

    }


    @When("Post the valid credentials")
    public void postTheValidCredentials() {

    }

    @Then("the user get valid response code {int}")
    public void theUserGetValidResponseCode(int arg0) {
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
