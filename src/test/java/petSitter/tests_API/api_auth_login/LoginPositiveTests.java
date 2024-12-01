package petSitter.tests_API.api_auth_login;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.AuthResponseDTO;
import petSitter.dto.UserDTO;
import petSitter.tests_API.TestBase;

import java.util.Base64;

import static io.restassured.RestAssured.given;

public class LoginPositiveTests extends TestBase {


    String password = "QWERTqwe123!";
    String email=System.currentTimeMillis()+"login@mail.test";
    int userId;
    AuthRequestDTO requestDTORegister = AuthRequestDTO.builder()
            .email(email)
            .password(password)
            .firstName("Jane")
            .lastName("Lo")
            .description("Checking users login")
            .build();


    AuthRequestDTO requestDTO = AuthRequestDTO.builder()
            .email(email)
            .password(password)
            .build();


    @BeforeMethod
    public void preCondition() {
        UserDTO response = given()
                .contentType(ContentType.JSON)
                .body(requestDTORegister)
                .when()
                .post("/auth/register")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().as(UserDTO.class);
        System.out.println("User зарегистрирован id:  " + response.getId());
        userId = response.getId();
    }


    @Test
    public void loginPositiveCheckStatusTest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/auth/login")
                .then()
                .assertThat()
                .statusCode(200).extract().response();
        System.out.println("Пользователь залогинился response:  " + response.asString());
        Assert.assertNotNull(response.asString());


    }


    @AfterMethod
    public void postCondition() {
        System.out.println("===AfterMethod===");
        Assert.assertTrue(deleteUserByEmail(email, password));

    }

}
