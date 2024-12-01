package petSitter.tests_API.api_auth_register_restore;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.UserDTO;
import petSitter.tests_API.TestBase;

import static io.restassured.RestAssured.given;

public class RecoveryAccountPositiveTests extends TestBase {
    String password = "QWERTqwe123!";
    String email = "recovery_account@mail.test";
    int userId;
    AuthRequestDTO requestDTORegister = AuthRequestDTO.builder()
            .email(email)
            .password(password)
            .firstName("Jane")
            .lastName("Lo")
            .description("Checking recovery account")
            .build();


    AuthRequestDTO requestDTO = AuthRequestDTO.builder()
            .email(email)
            .password(password)
            .build();


    @BeforeMethod
    public void preCondition() {
        try {
            UserDTO response = given()
                    .contentType(ContentType.JSON)
                    .body(requestDTORegister)
                    .when()
                    .post("/auth/register")
                    .then()
                    .extract().response().as(UserDTO.class);
            System.out.println("User зарегистрирован id:  " + response.getId());
            userId = response.getId();
        } catch (Exception e) {
            //throw new RuntimeException(e);

        }


        //String responseToken=getTokenAfterLogin(email, password);
        try {
            Assert.assertTrue(deleteUserByEmail(email, password));
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
        System.out.println("=====BeforeMethod закончил свою работу=========");

    }


    @Test
    public void recoveryAccountPositiveTest() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/auth/register/restore")
                .then()
                .assertThat()
                .statusCode(200).extract().response();
        System.out.println("Пользователь восстановил свой аккаунт response:  " + response.asString());
        System.out.println("=============================================================");
        System.out.println("Проверяем получение токена при логировании после восстановления аккаунта ");
        Assert.assertNotNull(getTokenAfterLogin(email, password));


    }


    @AfterMethod
    public void postCondition() {
        System.out.println("=====Работает автометод=========");
        Assert.assertTrue(deleteUserByEmail(email, password));

    }
}
