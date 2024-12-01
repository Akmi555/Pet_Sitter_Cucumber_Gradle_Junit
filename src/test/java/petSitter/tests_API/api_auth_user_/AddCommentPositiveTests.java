package petSitter.tests_API.api_auth_user_;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import petSitter.dto.*;
import petSitter.tests_API.TestBase;

import java.util.List;

import static io.restassured.RestAssured.given;

public class AddCommentPositiveTests extends TestBase {


    String email = "test1_user_sitter@mail.test";
    String password = "QWERTqwe123!";
    String email11 = "1732294206096getUserByEmail@mail.test";
    String password11 = "QWERTqwe123!";

    ReviewDTO commentNew = ReviewDTO.builder()
            .message("Hallo16")
            .stars(4)
            .build();


    @Test
    public void addCommentPositiveTest() {
        SoftAssert softAssert = new SoftAssert();
        String responseToken = getTokenAfterLogin(email, password);
        Response response = given()
                .header(AUTH, "Bearer " + responseToken)
                .contentType(ContentType.JSON)
                .body(commentNew)
                .when()
                .patch("/auth/user/" + email11)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
        System.out.println("" + response.asString());

        // Преобразование JSON в UserResponseDTO
        UserResponseDTO userResponse = response.as(UserResponseDTO.class);

        // Вывод деталей пользователя
        System.out.println("User ID: " + userResponse.getId());
        System.out.println("User Name: " + userResponse.getFirstName() + " " + userResponse.getLastName());
        System.out.println("Average Stars: " + userResponse.getAverageStars());

        // Вывод списка отзывов
        List<ReviewDTO> comments = userResponse.getReviews();
        ReviewDTO[] commentNewActual = new ReviewDTO[comments.size()];
       /*
        for (ReviewResponseDTO comment : comments) {
            System.out.println("ID комментария: " + comment.getId());
            System.out.println("Reviewer Email: " + comment.getReviewerEmail());
            System.out.println("Message: " + comment.getMessage());
            System.out.println("Stars: " + comment.getStars());
            System.out.println("-------------------------");
        }
*/
        for (int i = 0; i < comments.size(); i++) {
            ReviewDTO comment = comments.get(i);
            commentNewActual[i] = comment;
        }
        System.out.println(commentNewActual[comments.size() - 1].getMessage());
        softAssert.assertEquals(commentNewActual[comments.size() - 1].getMessage(), commentNew.getMessage());
        softAssert.assertEquals(commentNewActual[comments.size() - 1].getStars(), commentNew.getStars());
        softAssert.assertEquals(commentNewActual[comments.size() - 1].getReviewerEmail(), email);
        softAssert.assertAll();

    }
}
