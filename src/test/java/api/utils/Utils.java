package api.utils;

import api.pojo.AdditionResponse;
import api.pojo.EntityRequest;
import api.pojo.EntityResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.checkerframework.checker.units.qual.A;

import static api.utils.Creator.createEntityRequest;
import static io.restassured.RestAssured.given;

public class Utils {

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri("http://127.0.0.1")
                .setPort(8080)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
    }


    public static EntityResponse convertRequestToResponse(EntityRequest entityRequest, String id) {

        AdditionResponse additionResponse = new AdditionResponse();
        additionResponse.setId(Integer.parseInt(id));
        additionResponse.setAdditional_info(entityRequest.getAddition().getAdditional_info());
        additionResponse.setAdditional_number(entityRequest.getAddition().getAdditional_number());

        EntityResponse entityResponse = new EntityResponse();
        entityResponse.setId(Integer.parseInt(id));
        entityResponse.setTitle(entityRequest.getTitle());
        entityResponse.setVerified(entityRequest.isVerified());
        entityResponse.setAddition(additionResponse);
        entityResponse.setImportant_numbers(entityRequest.getImportant_numbers());

        return entityResponse;
    }
}
