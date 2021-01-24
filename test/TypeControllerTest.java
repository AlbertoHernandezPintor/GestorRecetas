import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

//Permite definir el orden de los test
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TypeControllerTest extends WithApplication {
    @Test
    public void test_a_createType() {
        ObjectNode body = Json.newObject();
        body.put("name", "Carne");
        body.put("description", "Comida procedente de animales");

        Http.RequestBuilder req = Helpers.fakeRequest()
                .method("POST")
                .uri("http://localhost:9000/type")
                .header("Accept", "application/json")
                .header("Authentication", "adminToken")
                .bodyJson(body);
        Result r = Helpers.route(app, req);
        Assert.assertEquals(200, r.status());
    }

    @Test
    public void test_b_deleteType() {
        Http.RequestBuilder req = Helpers.fakeRequest()
                .method("DELETE")
                .uri("http://localhost:9000/type?name=Carne")
                .header("Accept", "application/json")
                .header("Authentication", "adminToken");
        Result r = Helpers.route(app, req);
        Assert.assertEquals(200, r.status());
    }

    @Test
    public void test_c_getTypes() {
        Http.RequestBuilder req = Helpers.fakeRequest()
                .method("GET")
                .uri("http://localhost:9000/types")
                .header("Accept", "application/json");
        Result r = Helpers.route(app, req);
        Assert.assertEquals(200, r.status());
    }
}
