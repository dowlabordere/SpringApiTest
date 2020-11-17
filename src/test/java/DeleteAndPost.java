import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.codehaus.plexus.util.Base64;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DeleteAndPost extends BaseClass {

    @Test
    public void deleteIsSuccessful() throws IOException {

        HttpDelete request = new HttpDelete("https://api.github.com/repos/dowlabordere/dddddd");
        // token sam generisao na github nalogu u developer options, trebam da ga sakrijem negde nekako,
        // ovde je sakriven, probao sam i radi
        request.setHeader(HttpHeaders.AUTHORIZATION, "token " + enums.Credentials.TOKEN);
        response = client.execute(request);

        int actualStatusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatusCode, 204);
    }

    @Test
    public void createRepository() throws IOException {

        // Create an HttpPost with a valid endpoint
        HttpPost request = new HttpPost("https://api.github.com/user/repos");

        // Set the basic auth header
        String auth = enums.Credentials.EMAIL + ":" + enums.Credentials.PASSWORD;
        byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
        String autheHeader = "Basic " + new String(encodeAuth);
        request.setHeader(HttpHeaders.AUTHORIZATION, autheHeader);

        // Define Json to Post and set as Entity
        String json = "{\"name\": \"deleteme\"}";
        request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        // Send
        response = client.execute(request);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 201);
    }

}
