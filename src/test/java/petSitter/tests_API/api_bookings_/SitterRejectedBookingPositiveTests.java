package petSitter.tests_API.api_bookings_;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.BookingNewStatusDTO;
import petSitter.dto.ResponseServiceStatusDTO;
import petSitter.tests_API.TestBase;

import static io.restassured.RestAssured.given;

public class SitterRejectedBookingPositiveTests extends TestBase {

    //исполнитель
    String email = "test1_user_sitter@mail.test";
    String password = "QWERTqwe123!";
    //Заказчик
    String email11 = "1732294206096getUserByEmail@mail.test";
    String password11 = "QWERTqwe123!";
    String responseToken;


    AuthRequestDTO requestDTOReg = AuthRequestDTO.builder()
            .email(email)
            .password(password)
            .firstName("Mary")
            .lastName("Ann")
            .description("Добавляем услугу")
            .build();

    @Test
    public void userRejectedBookingPositiveTest() {
// логинется ситтер чтобы отклонить услугу
        responseToken = getTokenAfterLogin(email, password);
        // услугу бронирует заказчик
        BookingNewStatusDTO bookingCancelRequest = BookingNewStatusDTO.builder()
                .id(getIdAddBooking(email11, password11, email,password))
                .status("rejected")
                .build();

        ResponseServiceStatusDTO serviceDTO = given()
                .header(AUTH, "Bearer " + responseToken)
                .contentType(ContentType.JSON)
                .body(bookingCancelRequest)
                .when()
                .patch("/bookings/" + bookingCancelRequest.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().as(ResponseServiceStatusDTO.class);
        System.out.println("Booking Id :" + serviceDTO.getId());
        System.out.println("New status: "+serviceDTO.getStatus());
        Assert.assertEquals(serviceDTO.getStatus(), bookingCancelRequest.getStatus());

    }


    @Test
    public void userRejectedAfterConfirmedBookingPositiveTest(){
        responseToken = getTokenAfterLogin(email, password);
        // услугу бронирует заказчик
        BookingNewStatusDTO bookingCancelRequest = BookingNewStatusDTO.builder()
                .id(getIdAddBooking(email11, password11, email,password))
                .status("confirmed")
                .build();

        Response serviceDTO = given()
                .header(AUTH, "Bearer " + responseToken)
                .contentType(ContentType.JSON)
                .body(bookingCancelRequest)
                .when()
                .patch("/bookings/" + bookingCancelRequest.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
        System.out.println("Response :" + serviceDTO.asString());

        BookingNewStatusDTO bookingCancelRequest1 = BookingNewStatusDTO.builder()
                .id(bookingCancelRequest.getId())
                .status("rejected")
                .build();


        ResponseServiceStatusDTO serviceDTO1 = given()
                .header(AUTH, "Bearer " + responseToken)
                .contentType(ContentType.JSON)
                .body(bookingCancelRequest1)
                .when()
                .patch("/bookings/" + bookingCancelRequest.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().as(ResponseServiceStatusDTO.class);
        System.out.println("Bookings Id :" + serviceDTO1.getId());
        System.out.println("New status :" + serviceDTO1.getStatus());

        Assert.assertEquals(serviceDTO1.getStatus(), bookingCancelRequest1.getStatus());

    }

}
