package apps.university_app.controller;

import apps.university_app.model.dto.GradeDTO;
import apps.university_app.services.GradeServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeServices gradeService;

    @PostMapping
    public ResponseEntity<GradeDTO> createGrade(@Valid @RequestBody GradeDTO dto) {
        GradeDTO created = gradeService.createGrade(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradeDTO> updateGrade(@PathVariable Long id, @Valid @RequestBody GradeDTO dto) {
        GradeDTO updated = gradeService.updateGrade(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeDTO> getGradeById(@PathVariable Long id) {
        GradeDTO dto = gradeService.getGradeById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<GradeDTO>> getAllGrades() {
        List<GradeDTO> list = gradeService.getAllGrades();
        return ResponseEntity.ok(list);
    }
}