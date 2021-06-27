import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserStepDefs {
    @When("a user is authenticated")
    public void aUserIsAuthenticated() {

    }

    @And("^sends out a GET request to /accounts$")
    public void sendsOutAGETRequestToAccounts() {

    }

    @Then("^the client receives status code of {int}$")
    public void theClientReceivesStatusCodeOfInt() {
    }
}
