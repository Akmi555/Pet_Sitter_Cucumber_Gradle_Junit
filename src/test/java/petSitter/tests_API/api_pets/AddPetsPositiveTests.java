package petSitter.tests_API.api_pets;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.PetDTO;
import petSitter.tests_API.TestBase;

import static io.restassured.RestAssured.given;

public class AddPetsPositiveTests extends TestBase {
    String email = "test1_user_sitter@mail.test";
    String password = "QWERTqwe123!";
    String responseToken;
    AuthRequestDTO requestDTO = AuthRequestDTO.builder()
            .email(email)
            .password(password)
            .build();

    AuthRequestDTO requestDTOReg = AuthRequestDTO.builder()
            .email(email)
            .password(password)
            .firstName("Mary")
            .lastName("Ann")
            .description("Добавляем питомца")
            .build();

    PetDTO petNew = PetDTO.builder()
            .name("Rex")
            .type("Dog")
            .photo("jpeg")
            .build();

    @BeforeMethod
    public void preCondition() {

//        UserDTO userDTO = given()
//                .contentType(ContentType.JSON)
//                .body(requestDTOReg)
//                .when()
//                .post("/auth/register")
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .extract().response().as(UserDTO.class);
//        System.out.println("Пользователь зарегистрировался id:  " + userDTO.getId());

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
    public void addPetPositiveTest() {
        SoftAssert softAssert = new SoftAssert();
        PetDTO petDTO = given()
                .header(AUTH, "Bearer " + responseToken)
                .contentType(ContentType.JSON)
                .body(petNew)
                .when()
                .post("/pets")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().as(PetDTO.class);
        System.out.println("Id добавленного питомца: " + petDTO.getId());
        System.out.println("Имя питомца: " + petDTO.getName());
        System.out.println("Тип питомца: " + petDTO.getType());
        System.out.println("Фото питомца: " + petDTO.getPhoto());
        softAssert.assertEquals(petDTO.getName(), petNew.getName());
        softAssert.assertEquals(petDTO.getType(), petNew.getType());
        softAssert.assertEquals(petDTO.getPhoto(), petNew.getPhoto());
        softAssert.assertAll();
    }

}
