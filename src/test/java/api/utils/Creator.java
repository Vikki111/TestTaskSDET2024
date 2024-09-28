package api.utils;

import api.pojo.AdditionRequest;
import api.pojo.EntityRequest;
import org.apache.commons.lang3.RandomStringUtils;

public class Creator {

    public static EntityRequest createEntityRequest() {

        String title = RandomStringUtils.random(10, true, false);
        String addition = RandomStringUtils.random(10, true, false);
        String additionalNumber = RandomStringUtils.random(5, false, true);
        int[] importantNumbers = new int[]{Integer.parseInt(RandomStringUtils.random(5, false, true)),
                Integer.parseInt(RandomStringUtils.random(5, false, true))};

        EntityRequest entityRequest = new EntityRequest();
        entityRequest.setTitle(title);
        entityRequest.setVerified(true);
        entityRequest.setAddition(new AdditionRequest(addition, Integer.parseInt(additionalNumber)));
        entityRequest.setImportant_numbers(importantNumbers);

        return entityRequest;
    }
}
