package petSitter.tests_API.api_services__sitterId_;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.ServiceDTO;
import petSitter.tests_API.TestBase;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllServicesByUserPositiveTests extends TestBase {

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
            .description("Добавляем услугу")
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
    public void getAllServicesByUserPositiveTest() {
        int sittersId=getIdByUser(email, password);
        Response response = given()
                .header(AUTH, "Bearer " + responseToken)
                .contentType(ContentType.JSON)
                .when()
                .get("/services?sitterId="+sittersId)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        Gson gson = new Gson();
        List<ServiceDTO> services = response.jsonPath().getList("", ServiceDTO.class);
        String servicesJson = gson.toJson(services);
        System.out.println(servicesJson);
        for (ServiceDTO service : services) {
            System.out.println("ID: " + service.getId());
            System.out.println("Title : " + service.getTitle());
            System.out.println("Price : " + service.getPrice());
            System.out.println("Description : " + service.getDescription());

            System.out.println("-------------------------");

        }

    }

}
