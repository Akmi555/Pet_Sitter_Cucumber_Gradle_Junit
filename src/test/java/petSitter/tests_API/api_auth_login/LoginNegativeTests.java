package petSitter.tests_API.api_auth_login;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import petSitter.dto.AuthRequestDTO;
import petSitter.tests_API.TestBase;

import static io.restassured.RestAssured.given;

public class LoginNegativeTests extends TestBase {

    String email = "test1_user_sitter@mail.test";
    String password = "QWERTqwe123!!!";
    AuthRequestDTO requestDTO = AuthRequestDTO.builder()
            .email(email)
            .password(password)
            .build();


    @Test
    public void loginPositiveCheckStatusTest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/auth/login")
                .then()
                .assertThat()
                .statusCode(401).extract().response();
        System.out.println("Ошибка авторизации:  " + response.asString());
        Assert.assertEquals("",response.asString());


    }

}
