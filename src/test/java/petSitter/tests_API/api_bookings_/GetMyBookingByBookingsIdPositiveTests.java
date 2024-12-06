package petSitter.tests_API.api_bookings_;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.BookingDTO;
import petSitter.tests_API.TestBase;

import static io.restassured.RestAssured.given;

public class GetMyBookingByBookingsIdPositiveTests extends TestBase {

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

    //    BookingDTO bookingCancelRequest=BookingDTO.builder()
//            .status("cancelled")
//            .build();
    @BeforeMethod
    public void preCondition() {
        responseToken = getTokenAfterLogin(email, password);


    }

    @Test
    public void getMyBookingByBookingsIdPositiveTest() {
        int bookingId = 4;

        Response serviceDTO = given()
                .header(AUTH, "Bearer " + responseToken)
                .contentType(ContentType.JSON)
                .when()
                .get("/bookings/" + bookingId)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
        System.out.println("Response :" + serviceDTO.asString());

    }



    @Test
    public void ttttt(){

        String email = "test1_user_sitter@mail.test";
        String password = "QWERTqwe123!";
        String responseToken;
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .email(email)
                .password(password)
                .build();



    }

}
