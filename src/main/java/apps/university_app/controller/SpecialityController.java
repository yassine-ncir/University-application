package apps.university_app.controller;

import apps.university_app.model.dto.SpecialityDTO;
import apps.university_app.services.SpecialityServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialities")
@RequiredArgsConstructor
public class SpecialityController {

    private final SpecialityServices specialityService;

    @PostMapping
    public ResponseEntity<SpecialityDTO> createSpeciality(@Valid @RequestBody SpecialityDTO dto) {
        SpecialityDTO created = specialityService.createSpeciality(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialityDTO> updateSpeciality(@PathVariable Long id, @Valid @RequestBody SpecialityDTO dto) {
        SpecialityDTO updated = specialityService.updateSpeciality(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpeciality(@PathVariable Long id) {
        specialityService.deleteSpeciality(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialityDTO> getSpecialityById(@PathVariable Long id) {
        SpecialityDTO dto = specialityService.getSpecialityById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<SpecialityDTO>> getAllSpecialities() {
        List<SpecialityDTO> list = specialityService.getAllSpecialities();
        return ResponseEntity.ok(list);
    }
}
