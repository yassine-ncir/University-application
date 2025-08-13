package apps.university_app.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {

    private Long id;  // optional on create

    @NotBlank(message = "Full name is required")
    @Size(max = 150, message = "Full name must be at most 150 characters")
    private String fullName;

    @Size(max = 1000, message = "Bio must be at most 1000 characters")
    private String bio;

    @Email(message = "Email should be valid")
    @Size(max = 150)
    private String email;

    @Size(max = 20, message = "Phone must be at most 20 characters")
    private String phone;

    @NotNull(message = "Department ID is required")
    private Long departmentId;

    // Usually omit list of courses in this DTO to keep it light
}
