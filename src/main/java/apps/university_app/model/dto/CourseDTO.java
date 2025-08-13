package apps.university_app.model.dto;

import apps.university_app.model.enums.Semester;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private Long id;  // optional on create

    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Title must be at most 150 characters")
    private String title;

    @Size(max = 1000, message = "Description must be at most 1000 characters")
    private String description;

    @Min(value = 0, message = "Credits must be zero or more")
    private int credits;

    @DecimalMin(value = "0.0", inclusive = true, message = "Total hours must be zero or more")
    private Double totalHours;
    private Semester semester;
    private Long teacherId;
    @NotNull(message = "Grade ID is required")
    private Long gradeId;

    // Usually omit enrollments list for simplicity
    // private List<EnrollmentDTO> enrollments;
}
