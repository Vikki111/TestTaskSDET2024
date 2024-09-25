package api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityResponse {
    private int id;
    private String title;
    private boolean verified;
    private AdditionResponse addition;
    private int[] important_numbers;
}