package apps.university_app.services;

import apps.university_app.model.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentServices {
    DepartmentDTO createDepartment(DepartmentDTO dto);
    DepartmentDTO updateDepartment(Long id, DepartmentDTO dto);
    void deleteDepartment(Long id);
    DepartmentDTO getDepartmentById(Long id);
    List<DepartmentDTO> getAllDepartments();
}