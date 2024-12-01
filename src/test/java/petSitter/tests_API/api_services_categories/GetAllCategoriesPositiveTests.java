package petSitter.tests_API.api_services_categories;

import com.google.gson.Gson;
import org.testng.annotations.Test;
import petSitter.dto.CategoryDTO;
import petSitter.tests_API.TestBase;
import java.util.List;
import static io.restassured.RestAssured.given;

public class GetAllCategoriesPositiveTests extends TestBase {


    @Test
    public void getAllCategoriesTest(){
        List<CategoryDTO> categories = given()
                .when()
                .get("/services_categories")  // Убедитесь, что путь правильный
                .then()
                .assertThat()
                .statusCode(200)  // Ожидаем успешный ответ
                .extract().response().jsonPath().getList("",CategoryDTO.class);
        Gson gson=new Gson();
        String categoriesJson=gson.toJson(categories);
        System.out.println("JSON: "+categoriesJson);

        for(CategoryDTO category: categories){
            System.out.println("ID: "+category.getId());
            System.out.println("Title : "+category.getTitle());
            System.out.println("-------------------------");
        }
    }



}
