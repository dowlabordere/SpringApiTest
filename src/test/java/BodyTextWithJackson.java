import entities.NotFound;
import entities.RateLimit;
import entities.User;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class BodyTextWithJackson extends BaseClass{

    @Test
    public void returnsCorrectLogin() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/dowlabordere");
        response = client.execute(get);
        // moze i unmarshall generic
        User user = ResponseUtils.unmarshall(response, User.class);
        assertEquals(user.getLogin(), "dowlabordere");
    }

    @Test
    public void returnsCorrectId() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/dowlabordere");
        response = client.execute(get);
        User user = ResponseUtils.unmarshall(response, User.class);
        assertEquals(user.getId(), 20911080);
    }

    @Test
    public void notFoundMessageIsCorrect() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexistingendpoint");
        response = client.execute(get);
        NotFound notFoundMessage = ResponseUtils.unmarshallGeneric(response, NotFound.class);
        assertEquals(notFoundMessage.getMessage(), "Not Found");
    }

    @Test
    public void correctCoreLimitsAreSet() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
        response = client.execute(get);
        RateLimit rateLimit = ResponseUtils.unmarshallGeneric(response, RateLimit.class);
        assertEquals(rateLimit.getCoreLimit(), 60);
        assertEquals(rateLimit.getSearchLimit(), "10");
    }

}
