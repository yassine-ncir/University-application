package apps.university_app.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDTO {
    private Long id;
    @NotNull(message = "class id is required")
    @Size(min = 1)
    private Long classId;
    @NotNull(message = "course id is required")
    @Size(min = 1)
    private Long courseId;
}
