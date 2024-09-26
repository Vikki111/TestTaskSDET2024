package api.utils;

import api.pojo.EntityRequest;
import api.pojo.EntityResponse;

import static api.utils.Creator.createEntityRequest;
import static io.restassured.RestAssured.given;

public class BaseRequests {

    public static void deleteEntityById(String id) {
        given()
                .spec(Utils.getRequestSpecification())
                .when()
                .delete("/api/delete/" + id)
                .then()
                .statusCode(204);
    }


    public static EntityResponse createEntity() {
        EntityRequest entityRequest = createEntityRequest();

        String id = given()
                .spec(Utils.getRequestSpecification())
                .body(entityRequest)
//                .body(new File("src/test/resources/body.json"))
                .when()
                .post("/api/create")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        EntityResponse entityResponse = Utils.convertRequestToResponse(entityRequest, id);
        return entityResponse;
    }

    public static EntityResponse getEntityById(String id) {
        EntityResponse entityResponse = given()
                .when()
                .get("http://localhost:8080/api/get/" + id)
                .then()
                .statusCode(200)
                .extract()
                .as(EntityResponse.class);
        return entityResponse;
    }

}
