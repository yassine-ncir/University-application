package apps.university_app.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProgramDTO {
    private Long id;  // Optional on create, present on response

    @NotNull(message = "Degree program type is required")
    private String type;

    @NotNull(message = "Department ID is required")
    private Long departmentId;

}
