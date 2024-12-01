package petSitter.tests_API.api_auth_register;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.UserDTO;
import petSitter.tests_API.TestBase;
import petSitter.utils.DataProviders;

import static io.restassured.RestAssured.given;

public class RegisterWithInvalidEmailNegativeTests extends TestBase {




    @Test(dataProvider = "registerWithInvalidEmailNegativeFromCSV", dataProviderClass = DataProviders.class)
    public void registerWithInvalidEmailNegativeTest(AuthRequestDTO requestDTO) {
        int response = given()
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/auth/register")
                .then()
                .assertThat()
                .statusCode(400)
                .extract().statusCode();
        System.out.println("Ошибка регистрации: " + response);

    }



}
