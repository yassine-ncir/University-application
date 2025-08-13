package apps.university_app.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDTO {
    private Long id;
    @NotBlank(message = "name is required")
    @Size(max = 50,message = "name should be maximum 50 character")
    private String name;
    @NotNull(message = "number of student are required")
    @Min(value = 0, message = "Number of students must be zero or more")
    private int numOfStud;
    private Long grade_id;

}
