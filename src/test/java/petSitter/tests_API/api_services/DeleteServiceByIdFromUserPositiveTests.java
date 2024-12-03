package petSitter.tests_API.api_services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import petSitter.dto.AuthRequestDTO;
import petSitter.dto.ServiceDTO;
import petSitter.tests_API.TestBase;

import static io.restassured.RestAssured.given;

public class DeleteServiceByIdFromUserPositiveTests extends TestBase {

    String email = "test1_user_sitter@mail.test";
    String password = "QWERTqwe123!";


    @BeforeMethod
    public void preCondition() {
        addNewService(email, password, "Передержка собак", 500,
                "Только для собак мелких пород до 5 кг.", "png");


    }
    /*
    @Test
    public void deleteServicePositiveTest() {
        int[] idServicesBefore = getAllServicesBySitter(email, password);
        int capacityBefore = idServicesBefore.length;
        System.out.println("Количество услуг до удаления: " + capacityBefore);

        String responseToken = getTokenAfterLogin(email, password);
        Response serviceDTO = given()
                .header(AUTH, "Bearer " + responseToken)
                .contentType(ContentType.JSON)
                .when()
                .delete("/services/" + idServicesBefore[idServicesBefore.length - 1])
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
        System.out.println("Response :" + serviceDTO.asString());
        int[] idServicesAfter = getAllServicesBySitter(email, password, 13);
        int capacityAfter = idServicesAfter.length;
        System.out.println("Количество услуг после удаления: " + capacityAfter);
        Assert.assertEquals(capacityBefore, capacityAfter + 1);
    }

     */
}
