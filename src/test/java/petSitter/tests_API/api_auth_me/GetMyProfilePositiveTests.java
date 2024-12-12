package petSitter.tests_API.api_auth_me;


import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import petSitter.dto.UserDTO;
import petSitter.dto.UserResponseDTO;
import petSitter.tests_API.TestBase;
import static io.restassured.RestAssured.given;

public class GetMyProfilePositiveTests extends TestBase {

    String email = "test1_user_sitter@mail.test";
    String password = "QWERTqwe123!";




    UserDTO expectedUser=UserDTO.builder()
            .id(433)
            .firstName("FirstNameTest1")
            .lastName("LastNameTest2")
            .email("test1_user_sitter@mail.test")
            .build();


    @Test
    public void getMyProfilePositiveTest(){
        SoftAssert softAssert=new SoftAssert();

        String responseToken=getTokenAfterLogin(email,password);
        UserResponseDTO responseDTO = given()
                .header(AUTH, "Bearer " + responseToken)
                .when()
                .get("/auth/me")  // Убедитесь, что путь правильный
                .then()
                .assertThat()
                .statusCode(200)  // Ожидаем успешный ответ
                .extract().response().as(UserResponseDTO.class);
        System.out.println("Информация профиля юзера с id: "+responseDTO.getId());
        System.out.println("Name: "+responseDTO.getFirstName()+" "+ responseDTO.getLastName());
        System.out.println("Email: "+responseDTO.getEmail());
        System.out.println("Рейтинг:  "+responseDTO.getAverageStars());

        softAssert.assertTrue(responseDTO.getId()==expectedUser.getId());
        softAssert.assertEquals(responseDTO.getLastName(), expectedUser.getLastName());
        softAssert.assertEquals(responseDTO.getFirstName(), expectedUser.getFirstName());
        softAssert.assertEquals(responseDTO.getEmail(), expectedUser.getEmail());
        softAssert.assertAll();


    }
}
