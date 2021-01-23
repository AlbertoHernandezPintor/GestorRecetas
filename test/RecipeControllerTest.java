import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import play.Application;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

//Permite definir el orden de los test
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecipeControllerTest extends WithApplication {
    private String recipeTestName = "recipeTest7";

    @Test
    public void test_a_createRecipe() {
        ObjectNode body = Json.newObject();
        body.put("name", this.recipeTestName);
        body.put("type", "Vegana");
        body.put("time", "50");
        body.put("difficulty", "hard");

        ArrayNode ingredients = Json.newArray();
        ObjectNode ingredient1 = Json.newObject();
        ingredient1.put("name", "ternera");
        ingredient1.put("type", "Carnes");
        ingredients.add(ingredient1);
        body.put("ingredients", ingredients);

        ArrayNode allergens = Json.newArray();
        ObjectNode allergen1 = Json.newObject();
        allergen1.put("name", "mostaza");
        allergen1.put("diseases", "Alergia a la mostaza");
        allergens.add(allergen1);
        body.put("allergens", allergens);

        ArrayNode steps = Json.newArray();
        ObjectNode step1 = Json.newObject();
        step1.put("name", "Paso 1");
        step1.put("description", "Cocinar");
        steps.add(step1);
        body.put("steps", steps);

        Http.RequestBuilder req = Helpers.fakeRequest()
                .method("POST")
                .uri("http://localhost:9000/recipe")
                .header("Accept", "application/json")
                .bodyJson(body);
        Result r = Helpers.route(app, req);
        Assert.assertEquals(200, r.status());
    }

    @Test
    public void test_b_getRecipe() {
        Http.RequestBuilder req = Helpers.fakeRequest()
                .method("GET")
                .uri("http://localhost:9000/recipe?name=" + this.recipeTestName)
                .header("Accept", "application/json");
        Result r = Helpers.route(app, req);
        Assert.assertEquals(200, r.status());
    }

    @Test
    public void test_c_patchRecipe() {
        ObjectNode body = Json.newObject();
        body.put("name", this.recipeTestName);
        body.put("type", "Charra");
        body.put("time", "50");
        body.put("difficulty", "hard");

        ArrayNode ingredients = Json.newArray();
        ObjectNode ingredient1 = Json.newObject();
        ingredient1.put("name", "ternera");
        ingredient1.put("type", "Carnes");
        ingredients.add(ingredient1);
        body.put("ingredients", ingredients);

        ArrayNode allergens = Json.newArray();
        ObjectNode allergen1 = Json.newObject();
        allergen1.put("name", "mostaza");
        allergen1.put("diseases", "Alergia a la mostaza");
        allergens.add(allergen1);
        body.put("allergens", allergens);

        ArrayNode steps = Json.newArray();
        ObjectNode step1 = Json.newObject();
        step1.put("name", "Paso 1");
        step1.put("description", "Cocinar");
        steps.add(step1);
        body.put("steps", steps);

        Http.RequestBuilder req = Helpers.fakeRequest()
                .method("PATCH")
                .uri("http://localhost:9000/recipe")
                .header("Accept", "application/json")
                .bodyJson(body);
        Result r = Helpers.route(app, req);
        Assert.assertEquals(200, r.status());
    }

    @Test
    public void test_d_getRecipes() {
        Http.RequestBuilder req = Helpers.fakeRequest()
                .method("GET")
                .uri("http://localhost:9000/recipes?type=Charra")
                .header("Accept", "application/json");
        Result r = Helpers.route(app, req);
        Assert.assertEquals(200, r.status());
    }

    /*@Test
    public void test_e_deleteRecipe() {
        Http.RequestBuilder req = Helpers.fakeRequest()
                .method("DELETE")
                .uri("http://localhost:9000/recipe?name=" + this.recipeTestName)
                .header("Authorization", "userToken");

        Result r = Helpers.route(app, req);
        Assert.assertEquals(200, r.status());
    }*/
}