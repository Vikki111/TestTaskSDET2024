package api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityRequest {
    private String title;
    private boolean verified;
    private AdditionRequest addition;
    private int[] important_numbers;
}
