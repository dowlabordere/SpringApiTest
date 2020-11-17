import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class Get200 extends BaseClass{

    @DataProvider
    private Object[][] endpoints(){
        return new Object[][]{
                {"/"},
                {"/rate_limit"},
                {"/search/repositories?q=java"}
        };
    }

    @Test(dataProvider = "endpoints")
    public void baseUrlReturns200(String endpoint) throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + endpoint);
        response = client.execute(get);
        int actualStatus = response.getStatusLine().getStatusCode();
        assertEquals(actualStatus, 200);
    }

//    @Test
//    public void baseUrlReturns200() throws IOException {
//        HttpGet get = new HttpGet(BASE_ENDPOINT);
//        response = client.execute(get);
//        int actualStatus = response.getStatusLine().getStatusCode();
//        assertEquals(actualStatus, 200);
//    }
//
//    @Test
//    public void rateLimitReturns200() throws IOException {
//        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
//        response = client.execute(get);
//        int actualStatus = response.getStatusLine().getStatusCode();
//        assertEquals(actualStatus, 200);
//    }
//
//    @Test
//    public void searchReposReturns200() throws IOException {
//        HttpGet get = new HttpGet(BASE_ENDPOINT + "/search/repositories?q=java");
//        response = client.execute(get);
//        int actualStatus = response.getStatusLine().getStatusCode();
//        assertEquals(actualStatus, 200);
//    }

}
