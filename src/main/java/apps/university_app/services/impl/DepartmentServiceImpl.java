package apps.university_app.services.impl;

import apps.university_app.model.dto.DepartmentDTO;
import apps.university_app.model.entity.Department;
import apps.university_app.model.mapper.DepartmentMapper;
import apps.university_app.repository.DepartmentRepo;
import apps.university_app.services.DepartmentServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentServices {
    private final DepartmentRepo departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Transactional
    @Override
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO dto) {
        log.info("Updating a Department");

        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setEmail(dto.getEmail());
        existing.setLocation(dto.getLocation());

        Department updated = departmentRepository.save(existing);
        log.info("Department updated successfully");

        return departmentMapper.toDTO(updated);
    }

    @Transactional
    @Override
    public DepartmentDTO createDepartment(DepartmentDTO dto) {
        log.info("Creating a new Department");

        Department entity = departmentMapper.toEntity(dto);
        entity.setId(null);
        Department saved = departmentRepository.save(entity);

        log.info("Department created successfully");
        return departmentMapper.toDTO(saved);
    }

    @Transactional
    @Override
    public void deleteDepartment(Long id) {
        log.info("Deleting a Department");

        if (!departmentRepository.existsById(id)) {
            log.warn("Department not found for deletion");
            throw new EntityNotFoundException("Department not found");
        }

        departmentRepository.deleteById(id);

        log.info("Department deleted successfully");
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        log.info("Fetching a Department");

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));

        return departmentMapper.toDTO(department);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        log.info("Fetching all Departments");

        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toDTO)
                .toList();
    }
}
