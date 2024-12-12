package petSitter.tests_API.api_services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.CategoryDTO;
import petSitter.dto.ServiceDTO;
import petSitter.dto.UserDTO;
import petSitter.tests_API.TestBase;

import static io.restassured.RestAssured.given;

public class UpdateServicesPositiveTests extends TestBase {
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

    ServiceDTO service = ServiceDTO.builder()
            .id(939)
            .serviceCategory(1)
            .title("Кормить котика")
            .description("Кормить котика раз в день")
            .price(1000)
            .build();

    ServiceDTO serviceUpdate = ServiceDTO.builder()
            .title("Кормить котика")
            .description("Кормить котика 2 раза в день")
            .price(2000)
            .photo("dfg")
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
    public void updateServicePositiveTest() {
        Response serviceDTO = given()
                .header(AUTH, "Bearer " + responseToken)
                .contentType(ContentType.JSON)
                .body(serviceUpdate)
                .when()
                .patch("/services/" + service.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
        System.out.println("Response :" + serviceDTO.asString());


    }
}
