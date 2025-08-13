package apps.university_app.services.impl;

import apps.university_app.model.dto.ProgramDTO;
import apps.university_app.model.entity.Department;
import apps.university_app.model.entity.Program;
import apps.university_app.model.enums.DegreeProgType;
import apps.university_app.model.mapper.ProgramMapper;
import apps.university_app.repository.DepartmentRepo;
import apps.university_app.repository.ProgramRepo;
import apps.university_app.services.ProgramService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepo programRepository;
    private final DepartmentRepo departmentRepository;
    private final ProgramMapper programMapper;

    @Transactional
    @Override
    public void createProgram(ProgramDTO dto) {
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Department not found with his id "
                ));

        Program program = programMapper.toEntity(dto);

        // ✅ Set both sides of the relationship
        program.setDepartment(department);
        department.getPrograms().add(program);

        // ✅ Save from the owning side
        program.setId(null);
        programRepository.saveAndFlush(program);
    }

    @Transactional
    @Override
    public ProgramDTO updateProgram(Long id, ProgramDTO dto) {
        log.info("Updating a Program");

        Program existing = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program not found"));

        try {
            existing.setType(DegreeProgType.valueOf(dto.getType().toUpperCase()));
        } catch (IllegalArgumentException e) {
            log.error("Invalid DegreeProgType provided: {}", dto.getType());
            throw new IllegalArgumentException("Invalid DegreeProgType: " + dto.getType());
        }

        Department newDepartment = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));

        Department oldDepartment = existing.getDepartment();

        if (oldDepartment != null && !oldDepartment.equals(newDepartment)) {
            log.info("Changing Program's associated department");
            oldDepartment.getPrograms().remove(existing);
            departmentRepository.save(oldDepartment); // optional
        }

        existing.setDepartment(newDepartment);

        if (newDepartment.getPrograms() == null) {
            newDepartment.setPrograms(new java.util.ArrayList<>());
        }
        if (!newDepartment.getPrograms().contains(existing)) {
            newDepartment.getPrograms().add(existing);
        }

        Program updated = programRepository.save(existing);
        log.info("Program updated successfully");

        return programMapper.toDto(updated);
    }

    @Transactional
    @Override
    public void deleteProgram(Long id) {
        log.info("Deleting a Program");

        if (!programRepository.existsById(id)) {
            log.warn("Program not found for deletion");
            throw new EntityNotFoundException("Program not found");
        }

        programRepository.deleteById(id);

        log.info("Program deleted successfully");
    }

    @Override
    public ProgramDTO getProgramById(Long id) {
        log.info("Fetching a Program");

        Program program = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program not found"));

        return programMapper.toDto(program);
    }

    @Override
    public List<ProgramDTO> getAllPrograms() {
        log.info("Fetching all Programs");

        return programRepository.findAll()
                .stream()
                .map(programMapper::toDto)
                .toList();
    }
}
