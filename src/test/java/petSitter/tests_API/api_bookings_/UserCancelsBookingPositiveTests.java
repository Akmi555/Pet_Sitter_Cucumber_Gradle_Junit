package petSitter.tests_API.api_bookings_;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.BookingNewStatusDTO;
import petSitter.tests_API.TestBase;
import static io.restassured.RestAssured.given;
public class UserCancelsBookingPositiveTests extends TestBase {

    //Заказчик
    String email = "test1_user_sitter@mail.test";
    String password = "QWERTqwe123!";
    //исполнитель
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
    public void userCancelsBookingPositiveTest() {
        // логинется заказчик
        responseToken = getTokenAfterLogin(email, password);
// отказывает заказчик
        BookingNewStatusDTO bookingCancelRequest = BookingNewStatusDTO.builder()
                .id(getIdAddBooking(email, password, email11,password11))
                .status("cancelled")
                .build();
// отклоняет услугу ситтер
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

    }


//@Test
//    public void ttt(){
//    String email = "test1_user_sitter@mail.test";
//    String password = "QWERTqwe123!";
//      int arr[]=getAllPetsByUser(email, password);
//}


}
