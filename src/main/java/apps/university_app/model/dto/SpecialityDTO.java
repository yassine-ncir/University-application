package apps.university_app.model.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialityDTO {

    private Long id; // optional in create requests, filled in responses

    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @NotNull(message = "Program ID is required")
    private Long programId; // just the ID to link the program
    private Boolean available;


}
