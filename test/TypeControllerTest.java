import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Assert;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

public class TypeControllerTest extends WithApplication {
    @Test
    public void createTypeTest() {
        ObjectNode body = Json.newObject();
        body.put("name", "Carne");
        body.put("description", "Comida procedente de animales");

        Http.RequestBuilder req = Helpers.fakeRequest()
                .method("POST")
                .uri("http://localhost:9000/type")
                .header("Accept", "application/json")
                .bodyJson(body);
        Result r = Helpers.route(app, req);
        Assert.assertEquals(200, r.status());
    }

    @Test
    public void deleteTypeTest() {
        Http.RequestBuilder req = Helpers.fakeRequest()
                .method("DELETE")
                .uri("http://localhost:9000/type?name=Carne")
                .header("Accept", "application/json");
        Result r = Helpers.route(app, req);
        Assert.assertEquals(200, r.status());
    }

    @Test
    public void getTypesTest() {
        Http.RequestBuilder req = Helpers.fakeRequest()
                .method("GET")
                .uri("http://localhost:9000/types")
                .header("Accept", "application/json");
        Result r = Helpers.route(app, req);
        Assert.assertEquals(200, r.status());
    }
}
