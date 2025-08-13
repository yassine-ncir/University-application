package apps.university_app.model.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class GradeDTO {
    private Long id;
    @NotNull(message = "year is required")
    @Min(value = 0, message = "year must be at least 0")
    @Max(value = 100, message = "year must be at most 100")
    private int year;
    @NotNull(message = "speciality ID is required !")
    private Long speciality_id;

}
