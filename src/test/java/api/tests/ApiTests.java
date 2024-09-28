package api.tests;

import api.pojo.EntityRequest;
import api.pojo.EntityResponse;
import api.utils.BaseRequests;
import api.utils.Creator;
import api.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static api.utils.Creator.createEntityRequest;
import static io.restassured.RestAssured.given;

public class ApiTests {
//    Создание сущности: POST /api/create
//    Удаление сущности: DELETE /api/delete/{id}
//    Получение сущности: GET /api/get/{id}
//    Получение всех сущностей: GET /api/getAll
//    Обновление сущности: PATCH /api/patch/{id}

    public static String id;

    @Test
    public void createEntityTest() {
        EntityRequest entityRequest = createEntityRequest();

        String idOfCreatedEntity = given()
                .spec(Utils.getRequestSpecification())
                .body(entityRequest)
                .when()
                .post("/api/create")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        id = idOfCreatedEntity;

        EntityResponse actualEntityResponse = BaseRequests.getEntityById(idOfCreatedEntity);

        Assertions.assertEquals(Utils.convertRequestToResponse(entityRequest, idOfCreatedEntity), actualEntityResponse);
    }

    @Test
    public void deleteEntityTest() {
        EntityResponse createdEntity = BaseRequests.createEntity();
        id = "";

        given()
                .spec(Utils.getRequestSpecification())
                .when()
                .delete("/api/delete/" + createdEntity.getId())
                .then()
                .statusCode(204);
    }

    @Test
    public void getEntityTest() {
        EntityResponse expectedEntityResponse = BaseRequests.createEntity();
        id = String.valueOf(expectedEntityResponse.getId());

        EntityResponse actualEntityResponse = given()
                .spec(Utils.getRequestSpecification())
                .when()
                .get("/api/get/" + expectedEntityResponse.getId())
                .then()
                .statusCode(200)
                .extract()
                .as(EntityResponse.class);

        Assertions.assertEquals(expectedEntityResponse, actualEntityResponse);
    }

    @Test
    public void getAllEntitiesTest() {
        EntityResponse expectedEntityResponse = BaseRequests.createEntity();
        id = String.valueOf(expectedEntityResponse.getId());

        List<EntityResponse> actualEntityResponses = Arrays.asList(given()
                .spec(Utils.getRequestSpecification())
                .when()
                .get("/api/getAll")
                .then()
                .statusCode(200)
                .extract()
                .as(EntityResponse[].class));

        List<EntityResponse> expectedEntityResponses = new ArrayList<>();
        expectedEntityResponses.add(expectedEntityResponse);
        Assertions.assertIterableEquals(expectedEntityResponses, actualEntityResponses);
    }

    @Test
    public void updateEntityTest() {
        EntityResponse createdEntity = BaseRequests.createEntity();
        id = String.valueOf(createdEntity.getId());

        EntityRequest entityForPatch = Creator.createEntityRequest();

        given()
                .spec(Utils.getRequestSpecification())
                .body(entityForPatch)
                .when()
                .patch("/api/patch/" + createdEntity.getId())
                .then()
                .statusCode(204);

        EntityResponse actualEntityResponse = BaseRequests.getEntityById(String.valueOf(createdEntity.getId()));

        EntityResponse expectedUpdatedEntity = Utils.convertRequestToResponse(entityForPatch, String.valueOf(createdEntity.getId()));

        Assertions.assertEquals(expectedUpdatedEntity, actualEntityResponse);
    }

    @AfterEach
    public void deleteEntitiesById() {
        if (!id.equals("")) {
            BaseRequests.deleteEntityById(id);
        }
    }

}
