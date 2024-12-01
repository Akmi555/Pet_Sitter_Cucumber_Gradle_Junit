package petSitter.tests_API;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import petSitter.dto.*;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TestBase {
    public final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cGRhdHRlc3RAbWFpbC5jb20iLCJpYXQiOjE3MzIxODExMTIsImV4cCI6MTczMjE4NDcxMn0.mUjtTFmEqDPn4jmwfbX4cMw__xtCR-BWRRom4ZCxpYQ";
    public final String AUTH = "Authorization";


    @BeforeMethod
    public void init() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "api";
    }

    public boolean deleteUserByEmail(String email, String password) {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .email(email)
                .password(password)
                .build();
        //логинемся
        String responseToken = getTokenAfterLogin(email, password);
//         responseToken = given()
//                .contentType(ContentType.JSON)
//                .body(requestDTO)
//                .when()
//                .post("/auth/login")
//                .then()
//                .assertThat()
//                .statusCode(200).extract().path("token");
//
//        System.out.println("Пользователь залогинился responseToken:  " + responseToken);

// удаление пользователя по id

        Response responseDTO = given()
                .header(AUTH, "Bearer " + responseToken)
                .when()
                .delete("/auth/me/" + email)  // Убедитесь, что путь правильный
                .then()
                  // Ожидаем успешный ответ
                .extract().response();

        System.out.println("Пользователь удален успешно  response: " + responseDTO.asString());
// Логинимся с данными удаленного пользователя
        int response = given()
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/auth/login")
                .then()
                .assertThat()
                .statusCode(403).extract().statusCode();
        System.out.println("Такой пользователь удален или  не зарегистрирован!");
        return true;
    }


    public String getTokenAfterLogin(String email, String password) {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .email(email)
                .password(password)
                .build();

        String responseToken = given()
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/auth/login")
                .then()
                .extract().path("token");

        System.out.println("Пользователь залогинился responseToken:  " + responseToken);
        return responseToken;
    }

    public int getIdByUser(String email, String password) {
        String responseToken = getTokenAfterLogin(email, password);
        int userId = given()
                .header(AUTH, "Bearer " + responseToken)
                .when()
                .get("/auth/user/" + email)  // Убедитесь, что путь правильный
                .then()
                .assertThat()
                .statusCode(200)  // Ожидаем успешный ответ
                .extract().path("id");
        System.out.println("userId: " + userId);
        return userId;

    }


    public int getIdAddBooking(String emailOwner, String passwordOwner, int idSitter) {

//получить все сервисы ситтерра (idSitter)
        int[] idServices = getAllServicesBySitter(emailOwner, passwordOwner, idSitter);
        int[] idPets=getAllPetsByUser(emailOwner, passwordOwner);


        String responseToken = getTokenAfterLogin(emailOwner, passwordOwner);
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .email(emailOwner)
                .password(passwordOwner)
                .build();

        AuthRequestDTO requestDTOReg = AuthRequestDTO.builder()
                .email(emailOwner)
                .password(passwordOwner)
                .firstName("Mary")
                .lastName("Ann")
                .description("Добавляем питомца")
                .build();

        BookingDTO bookingDTO = BookingDTO.builder()
                .serviceId(idServices[0])
                .petId(idPets[0])
                .startDate("2025-02-02")
                .endDate("2025-05-05")
                .build();


        SoftAssert softAssert = new SoftAssert();
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
        softAssert.assertEquals(booking.getStartDate(), bookingDTO.getStartDate());
        softAssert.assertEquals(booking.getEndDate(), bookingDTO.getEndDate());
        softAssert.assertEquals(booking.getServiceId(), bookingDTO.getServiceId());
        softAssert.assertEquals(booking.getPetId(), bookingDTO.getPetId());
        softAssert.assertAll();

        return booking.getId();

    }

    public int[] getAllServicesBySitter(String emailOwner, String passwordOwner, int idSitter) {
        //int sittersId=getIdByUser(email, password);

        String responseToken = getTokenAfterLogin(emailOwner, passwordOwner);
        Response response = given()
                .header(AUTH, "Bearer " + responseToken)
                .contentType(ContentType.JSON)
                .when()
                .get("/services?sitterId=" + idSitter)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        Gson gson = new Gson();
        List<ServiceDTO> services = response.jsonPath().getList("", ServiceDTO.class);
        String servicesJson = gson.toJson(services);
        int[] idServices = new int[services.size()];
        for (int i = 0; i < services.size(); i++) {
            ServiceDTO service = services.get(i);
            idServices[i] = service.getId();

            // Печать информации об услуге
            System.out.println("ID: " + service.getId());
            System.out.println("Title: " + service.getTitle());
            System.out.println("Price: " + service.getPrice());
            System.out.println("Description: " + service.getDescription());
            System.out.println("-------------------------");
        }

        // Печатаем массив ID услуг
        System.out.println("Все ID услуг выбранного ситтера: " + Arrays.toString(idServices));

        return idServices;
    }


    public int[] getAllPetsByUser(String emailOwner, String passwordOwner) {

        String responseToken = getTokenAfterLogin(emailOwner, passwordOwner);
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
        // System.out.println(petsJson);
        int[] idPets = new int[pets.size()];
        for (int i = 0; i < pets.size(); i++){
            PetDTO pet = pets.get(i);
            idPets[i] = pet.getId();
            System.out.println("ID: " + pet.getId());
            System.out.println("Name : " + pet.getName());
            System.out.println("Type : " + pet.getType());
            System.out.println("Photo : " + pet.getPhoto());
            System.out.println("User : " + pet.getUser().getLastName() + " " + pet.getUser().getFirstName());

            System.out.println("-------------------------");
            System.out.println("Все ID питомцев юзера: " + Arrays.toString(idPets));

        }
        return idPets;
    }

    public void addPet(String emailOwner, String passwordOwner, String petName, String petType ){
        String responseToken=getTokenAfterLogin(emailOwner, passwordOwner);

        PetDTO petNew=PetDTO.builder()
                .name(petName)
                .type(petType)
                .build();
        PetDTO petDTO = given()
                .header(AUTH, "Bearer " + responseToken)
                .contentType(ContentType.JSON)
                .body(petNew)
                .when()
                .post("/pets")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().as(PetDTO.class);
        System.out.println("Id добавленного питомца: " + petDTO.getId());
    }


public void addNewService(String email, String password, String serviceTitle, double price, String description, String photo){
    int[] idCategories=getAllCategories();

        ServiceDTO serviceNew=ServiceDTO.builder()
                .title(serviceTitle)
                .price(price)
                .photo(photo)
                .description(description)
                .serviceCategory(idCategories[0])
                .build();

   String responseToken = getTokenAfterLogin(email, password);
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

}

    public int[] getAllCategories() {
        List<CategoryDTO> categories = given()
                .when()
                .get("/services_categories")  // Убедитесь, что путь правильный
                .then()
                .assertThat()
                .statusCode(200)  // Ожидаем успешный ответ
                .extract().response().jsonPath().getList("", CategoryDTO.class);
        Gson gson = new Gson();
        String categoriesJson = gson.toJson(categories);
       // System.out.println("JSON: " + categoriesJson);
        int[] idCategories= new int[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            CategoryDTO category = categories.get(i);
            idCategories[i] = category.getId();
//            System.out.println("ID: " + category.getId());
//            System.out.println("Title : " + category.getTitle());
//            System.out.println("-------------------------");

        }
        System.out.println("Все ID категорий: " + Arrays.toString(idCategories));
        return idCategories;
    }



}
