package petSitter.tests_API.api_auth_me;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.UserDTO;
import petSitter.tests_API.TestBase;

import static io.restassured.RestAssured.given;


public class UpdateUsersProfilePositiveTests extends TestBase {

    String email = System.currentTimeMillis() + "update@mail.test";
    String password = "QWERTqwe123!";
    int userRegisterById;
    int userIdAfterUpdate;

    AuthRequestDTO requestDTOReg = AuthRequestDTO.builder()
            .email(email)
            .password(password)
            .firstName("Ann")
            .lastName("Red")
            .build();

    UserDTO userUpdate = UserDTO.builder()
            .firstName("Beth")
            .lastName("Dastin")
            .description("Checking users update")
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
        userRegisterById = userDTO.getId();

    }


    @Test
    public void updateUserPositiveTest() {
        SoftAssert softAssert = new SoftAssert();
        String responseToken = getTokenAfterLogin(email, password);
        UserDTO responseDTO = given()
                .header(AUTH, "Bearer " + responseToken)
                .contentType(ContentType.JSON)
                .body(userUpdate)
                .when()
                .put("/auth/me")
                .then()
                .statusCode(200)
                .extract()
                .as(UserDTO.class);
        System.out.println("Id после update: " + responseDTO.getId());
        System.out.println("New firstName: " + responseDTO.getFirstName());
        System.out.println("New lastName:  " + responseDTO.getLastName());
        System.out.println("New description:  " + responseDTO.getDescription());
        System.out.println("New photo: " + responseDTO.getPhoto());
        userIdAfterUpdate = responseDTO.getId();
        softAssert.assertTrue(responseDTO.getId() == userRegisterById);
        softAssert.assertEquals(responseDTO.getLastName(), userUpdate.getLastName());
        softAssert.assertEquals(responseDTO.getFirstName(), userUpdate.getFirstName());
        softAssert.assertEquals(responseDTO.getDescription(), userUpdate.getDescription());
        softAssert.assertEquals(responseDTO.getPhoto(), userUpdate.getPhoto());
        softAssert.assertAll();

    }


    @AfterMethod
    public void postCondition() {
        System.out.println("=======Автометод=========");
        Assert.assertTrue(deleteUserByEmail(email, password));

    }

}
