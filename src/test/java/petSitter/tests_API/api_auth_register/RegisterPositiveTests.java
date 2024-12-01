package petSitter.tests_API.api_auth_register;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.UserDTO;
import petSitter.tests_API.TestBase;
import static io.restassured.RestAssured.given;

public class RegisterPositiveTests extends TestBase {

String emailNew=System.currentTimeMillis()+"register@mail.test";
String password="QWERTqwe123!";
String emailRegister;

    AuthRequestDTO requestDTO = AuthRequestDTO.builder()
            .email(emailNew)
            .password(password)
            .firstName("Mary")
            .lastName("Popins")
            .description("Checking users registration")
            .build();


    @Test
    public void registerUserPositiveTest(){
        UserDTO response=given()
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/auth/register")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().as(UserDTO.class);
        System.out.println("User зарегистрирован id:  "+response.getId());
       emailRegister=response.getEmail();

        Assert.assertNotNull(response.getId(),"User зарегистрирован, но User_Id не присвоен");
    }


    @AfterMethod
    public void postCondition() {

       Assert.assertTrue( deleteUserByEmail(emailRegister, password));

    }

}
