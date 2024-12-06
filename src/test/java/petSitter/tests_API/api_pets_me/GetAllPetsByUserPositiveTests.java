package petSitter.tests_API.api_pets_me;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.PetDTO;
import petSitter.tests_API.TestBase;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllPetsByUserPositiveTests extends TestBase {

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
    public void GetAllPetsByUserPositiveTest() {
        Response response = given()
                .header(AUTH, "Bearer " + responseToken)
                .when()
                .get("/pets/me")  // Убедитесь, что путь правильный
                .then()
                .assertThat()
                .statusCode(200)// Ожидаем успешный ответ
                .extract().response();

        Gson gson = new Gson();
        List<PetDTO> pets = response.jsonPath().getList("", PetDTO.class);
        String petsJson = gson.toJson(pets);
        //System.out.println(petsJson);

        for (PetDTO pet : pets) {
            System.out.println("ID: " + pet.getId());
            System.out.println("Name : " + pet.getName());
            System.out.println("Type : " + pet.getType());
            System.out.println("Photo : " + pet.getPhoto());
          //  System.out.println("User : " + pet.getUser().getLastName() + " " + pet.getUser().getFirstName());

            System.out.println("-------------------------");

        }
    }

}
