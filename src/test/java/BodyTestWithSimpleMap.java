import entities.User;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;

import static entities.User.LOGIN;
import static org.testng.Assert.assertEquals;

public class BodyTestWithSimpleMap extends BaseClass{

    @Test
    public void returnsCorrectLogin() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/dowlabordere");
        response = client.execute(get);
        String jsonBody = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(jsonBody);
        String loginValue = (String) jsonObject(jsonObject, LOGIN);
        assertEquals(loginValue, "dowlabordere");
    }

    @Test
    public void returnsCorrectId() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/dowlabordere");
        response = client.execute(get);
        String jsonBody = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(jsonBody);
        Integer idValue = (Integer) jsonObject(jsonObject, User.ID);
        assertEquals(idValue, Integer.valueOf(20911080));
    }

    private Object jsonObject(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }

}
