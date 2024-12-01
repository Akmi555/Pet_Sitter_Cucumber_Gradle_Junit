package petSitter.tests_API.api_bookings_;

import io.cucumber.java.eo.Se;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.BookingDTO;
import petSitter.dto.PetDTO;
import petSitter.dto.ServiceDTO;
import petSitter.tests_API.TestBase;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

public class AddBookingPositiveTests extends TestBase {

    String email = "test1_user_sitter@mail.test";
    String password = "QWERTqwe123!";


    BookingDTO bookingDTO = BookingDTO.builder()
            .serviceId(1)
            .petId(2)
            .startDate("2025-02-02")
            .endDate("2025-05-05")
            .build();



    @Test
    public void addBookingPositiveTest() {
        SoftAssert softAssert = new SoftAssert();
        String responseToken=getTokenAfterLogin(email, password);
        BookingDTO booking = given()
                .header(AUTH, "Bearer " + responseToken)
                .contentType(ContentType.JSON)
                .body(bookingDTO)
                .when()
                .post("/bookings")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().as(BookingDTO.class);
        System.out.println("Даты бронирования: " + booking.getStartDate() + "-" + booking.getEndDate());
        System.out.println("ID услуги: " + booking.getServiceId());
        System.out.println("Id вашей брони:" + booking.getId());
        System.out.println("Статус вашей брони " + booking.getStatus());
        System.out.println("Сумма оплаты итого: "+ booking.getPrice());
        softAssert.assertEquals(booking.getStartDate(), bookingDTO.getStartDate());
        softAssert.assertEquals(booking.getEndDate(), bookingDTO.getEndDate());
        softAssert.assertEquals(booking.getServiceId(), bookingDTO.getServiceId());
        softAssert.assertEquals(booking.getPetId(), bookingDTO.getPetId());
        softAssert.assertAll();

    }

}
