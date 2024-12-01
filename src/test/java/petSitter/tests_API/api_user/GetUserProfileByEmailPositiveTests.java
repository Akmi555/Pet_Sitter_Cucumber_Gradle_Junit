package petSitter.tests_API.api_user;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.UserDTO;
import petSitter.tests_API.TestBase;

import static io.restassured.RestAssured.given;

public class GetUserProfileByEmailPositiveTests extends TestBase {

   // String email = System.currentTimeMillis() + "getUserByEmail@mail.test";
   String email = "test1_user_sitter@mail.test";
    String password = "QWERTqwe123!";
    String responseToken;

    AuthRequestDTO requestDTOReg = AuthRequestDTO.builder()
            .email(email)
            .password(password)
            .firstName("Ann")
            .lastName("Red")
            .description("Checking get users profile")
            .build();
    AuthRequestDTO requestDTO = AuthRequestDTO.builder()
            .email(email)
            .password(password)
            .build();

    @BeforeMethod
    public void preCondition() {

        try {
            UserDTO userDTO = given()
                    .contentType(ContentType.JSON)
                    .body(requestDTOReg)
                    .when()
                    .post("/auth/register")
                    .then()
                    .extract().response().as(UserDTO.class);
            System.out.println("Пользователь зарегистрировался id:  " + userDTO.getId());
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }

        System.out.println("========BeforeMethod закончил свою работу========");
    }

    @Test
    public void getUsersProfilePositiveTest() {
        String responseToken=getTokenAfterLogin(email, password);
        Response responseDTO = given()
                .header(AUTH, "Bearer " + responseToken)
                .when()
                .get("/auth/user/" + email)  // Убедитесь, что путь правильный
                .then()
                .assertThat()
                .statusCode(200)  // Ожидаем успешный ответ
                .extract().response();
        System.out.println(""+responseDTO.asString());

    }
}
