package apps.university_app.model.dto;


import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;  // Optional for create, present in responses

    @NotBlank(message = "Full name is required")
    @Size(max = 150, message = "Full name must be at most 150 characters")
    private String fullName;
    @Email(message = "Email should be valid")
    @Size(max = 150)
    private String email;
    @NotBlank(message = "CIN is required")
    @Size(max = 20, message = "CIN must be at most 20 characters")
    private String cin;
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    @NotNull(message = "Degree program type is required")
    private String degreeType;
    @NotNull(message = "Student status is required")
    private String status;
    @NotNull(message = "Class ID is required")
    private Long classId;
}
