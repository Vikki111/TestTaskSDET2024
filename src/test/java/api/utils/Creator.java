package api.utils;

import api.pojo.AdditionRequest;
import api.pojo.EntityRequest;

public class Creator {

    public static EntityRequest createEntityRequest() {
        EntityRequest entityRequest = new EntityRequest();
        entityRequest.setTitle("test title 1");
        entityRequest.setVerified(true);
        entityRequest.setAddition(new AdditionRequest("test addidtion 1", 123));
        entityRequest.setImportant_numbers(new int[] {12, 344});

        return entityRequest;
    }
}
