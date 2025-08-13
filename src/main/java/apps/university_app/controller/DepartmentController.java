package apps.university_app.controller;

import apps.university_app.model.dto.DepartmentDTO;
import apps.university_app.services.DepartmentServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments/")
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentServices departmentServices;

    @GetMapping()
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        return ResponseEntity.ok(departmentServices.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable Long id){
        DepartmentDTO d = departmentServices.getDepartmentById(id);
        return ResponseEntity.ok(d);
    }

    @PostMapping()
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO dto){
        DepartmentDTO created = departmentServices.createDepartment(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id,@Valid @RequestBody DepartmentDTO dto){
        DepartmentDTO updated = departmentServices.updateDepartment(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id){
        departmentServices.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

}
