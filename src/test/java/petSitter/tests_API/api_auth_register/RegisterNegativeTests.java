package petSitter.tests_API.api_auth_register;

import com.beust.ah.A;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.UserDTO;
import petSitter.tests_API.TestBase;
import petSitter.utils.DataProviders;

import static io.restassured.RestAssured.given;

public class RegisterNegativeTests extends TestBase {


    AuthRequestDTO requestDTO;

    @Test(dataProvider = "registerNegativeFromCSV", dataProviderClass = DataProviders.class)
    public void registerUserNegativeTest(AuthRequestDTO requestDTO1) {
        this.requestDTO = requestDTO1;
        UserDTO responseBeforeMethod = given()
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/auth/register")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().as(UserDTO.class);
        System.out.println("User зарегистрирован id:  " + responseBeforeMethod.getId());
        String emailReg = requestDTO.getEmail();
        String passwordReg = requestDTO.getPassword();


        int response = given()
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/auth/register")
                .then()
                .assertThat()
                .statusCode(400)
                .extract().statusCode();
        System.out.println("Ошибка при повторной регистрации с данными уже зарегистрированного пользователя: " + response);

    }

    @AfterMethod
    public void postCondition() {
        Assert.assertTrue(deleteUserByEmail(requestDTO.getEmail(), requestDTO.getPassword()));

    }

}
