package api.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static api.utils.Creator.createEntityRequest;
import static io.restassured.RestAssured.given;

public class ApiTests {
//    Создание сущности: POST /api/create
//    Удаление сущности: DELETE /api/delete/{id}
//    Получение сущности: GET /api/get/{id}
//    Получение всех сущностей: GET /api/getAll
//    Обновление сущности: PATCH /api/patch/{id}
    @Test
    public void createEntityTest() {
        String id = given()
                .body(createEntityRequest())
                .when()
                .post("http://localhost:8080/api/create")
                .then()
                .statusCode(201)
                .extract()
                .asString();
        Assertions.assertEquals(id, "1");
    }

    @Test
    public void deleteEntityTest() {
        given()
                .body(createEntityRequest())
                .when()
                .post("http://localhost:8080/api/delete/2")
                .then()
                .statusCode(204);
    }
}
