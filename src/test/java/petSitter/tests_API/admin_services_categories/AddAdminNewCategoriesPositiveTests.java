package petSitter.tests_API.admin_services_categories;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.CategoryDTO;
import petSitter.tests_API.TestBase;

import static io.restassured.RestAssured.given;

public class AddAdminNewCategoriesPositiveTests extends TestBase {


    String email = "admin@mail.test";
    String password = "QWERTqwe123!";


    AuthRequestDTO requestDTOReg = AuthRequestDTO.builder()
            .email(email)
            .password(password)
            .firstName("Admin")
            .lastName("Admin")
            .build();

    CategoryDTO categoryDTONew = CategoryDTO.builder()
            .title("Уход за собаками крупных пород.")
            .build();

//    @BeforeMethod
//    public void preCondition() {
//        UserDTO userDTO = given()
//                .contentType(ContentType.JSON)
//                .body(requestDTOReg)
//                .when()
//                .post("http://localhost:8080/api/auth/register")
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .extract().response().as(UserDTO.class);
//        System.out.println("Admin зарегистрировался id:  " + userDTO.getId());
    // }


    @Test
    public void addAdminNewCategoriesTest() {
        String responseToken = getTokenAfterLogin(email, password);
        CategoryDTO category = given()
                .header(AUTH, "Bearer " + responseToken)
                .contentType(ContentType.JSON)
                .body(categoryDTONew)
                .when()
                .post("/admin/services_categories")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().as(CategoryDTO.class);
        System.out.println("Id добавленной категории: " + category.getId());
        System.out.println("Title добавленной категории: " + category.getTitle());
        System.out.println("====================================");
        Assert.assertEquals(category.getTitle(), categoryDTONew.getTitle());

    }

}
