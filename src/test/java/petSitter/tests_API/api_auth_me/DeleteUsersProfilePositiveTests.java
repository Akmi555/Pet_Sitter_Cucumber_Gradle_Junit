package petSitter.tests_API.api_auth_me;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.UserDTO;
import petSitter.tests_API.TestBase;

import static io.restassured.RestAssured.given;

public class DeleteUsersProfilePositiveTests extends TestBase {

    String email = System.currentTimeMillis() + "delete@mail.test";
    String password = "QWERTqwe123!";
    String responseToken;

    AuthRequestDTO requestDTO = AuthRequestDTO.builder()
            .email(email)
            .password(password)
            .build();

    AuthRequestDTO requestDTOReg = AuthRequestDTO.builder()
            .email(email)
            .password(password)
            .firstName("Jack")
            .lastName("Nikols")
            .description("Checking users delete")
            .build();

    @BeforeMethod
    public void preCondition() {

        UserDTO userDTO = given()
                .contentType(ContentType.JSON)
                .body(requestDTOReg)
                .when()
                .post("/auth/register")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().as(UserDTO.class);
        System.out.println("Пользователь зарегистрировался id:  " + userDTO.getId());

        responseToken = given()
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/auth/login")
                .then()
                .assertThat()
                .statusCode(200).extract().path("token");

        System.out.println("Пользователь залогинился responseToken:  " + responseToken);
    }


    @Test
    public void deleteUsersProfilePositiveTest() {
        Assert.assertTrue(deleteUserByEmail(email, password), "Пользователь не удален");

    }

}
