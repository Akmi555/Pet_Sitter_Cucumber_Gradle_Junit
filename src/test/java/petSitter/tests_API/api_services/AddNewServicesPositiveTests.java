package petSitter.tests_API.api_services;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import petSitter.dto.*;
import petSitter.tests_API.TestBase;

import java.util.List;

import static io.restassured.RestAssured.given;

public class AddNewServicesPositiveTests extends TestBase {

    String email = "test1_user_sitter@mail.test";
    String password = "QWERTqwe123!";
    String responseToken;
    int serviceCategory = 2;
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

    ServiceDTO serviceNew = ServiceDTO.builder()
            .serviceCategory(serviceCategory)
            .title("Выгуливать пёсика")
            .description("Выгуливать песика 3 раза в день")
            .price(500)
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


    }

    @Test
    public void addNewServicesPositiveTest() {
        responseToken = getTokenAfterLogin(email, password);
        SoftAssert softAssert = new SoftAssert();
        NewServiceDTO response = given()
                .header(AUTH, "Bearer " + responseToken)
                .contentType(ContentType.JSON)
                .body(serviceNew)
                .when()
                .post("/services")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().as(NewServiceDTO.class);
        System.out.println("Id добавленного сервиса: " + response.getId());
        System.out.println("Title сервиса: " + response.getTitle());
        System.out.println("Price: " + response.getPrice());
        System.out.println("Description:  " + response.getDescription());

        softAssert.assertEquals(response.getTitle(), serviceNew.getTitle());
        softAssert.assertEquals(response.getPrice(), serviceNew.getPrice());
        softAssert.assertEquals(response.getDescription(), serviceNew.getDescription());
        softAssert.assertAll();

    }


}
