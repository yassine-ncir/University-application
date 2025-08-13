package apps.university_app.services.impl;

import apps.university_app.model.dto.GradeDTO;
import apps.university_app.model.entity.Grade;
import apps.university_app.model.entity.Speciality;
import apps.university_app.model.mapper.GradeMapper;
import apps.university_app.repository.GradeRepo;
import apps.university_app.repository.SpecialityRepo;
import apps.university_app.services.GradeServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GradeServiceImpl implements GradeServices {

    private final GradeRepo gradeRepository;
    private final SpecialityRepo specialityRepository;
    private final GradeMapper gradeMapper;

    @Override
    public GradeDTO createGrade(GradeDTO dto) {
        log.info("Creating a new Grade");
        Grade grade = gradeMapper.toEntity(dto);

        Speciality speciality = specialityRepository.findById(dto.getSpeciality_id())
                .orElseThrow(() -> new EntityNotFoundException("Speciality not found"));
        grade.setSpeciality(speciality);
        grade.setId(null);
        Grade saved = gradeRepository.save(grade);
        log.info("Grade created successfully");
        return gradeMapper.toDTO(saved);
    }

    @Override
    public GradeDTO updateGrade(Long id, GradeDTO dto) {
        log.info("Updating a Grade");
        Grade existing = gradeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grade not found"));

        existing.setYear(dto.getYear());

        Speciality speciality = specialityRepository.findById(dto.getSpeciality_id())
                .orElseThrow(() -> new EntityNotFoundException("Speciality not found"));
        existing.setSpeciality(speciality);

        Grade updated = gradeRepository.save(existing);
        log.info("Grade updated successfully");
        return gradeMapper.toDTO(updated);
    }

    @Override
    public void deleteGrade(Long id) {
        log.info("Deleting a Grade");
        if (!gradeRepository.existsById(id)) {
            log.warn("Grade not found for deletion");
            throw new EntityNotFoundException("Grade not found");
        }
        gradeRepository.deleteById(id);
        log.info("Grade deleted successfully");
    }

    @Override
    public GradeDTO getGradeById(Long id) {
        log.info("Fetching a Grade");
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grade not found"));
        return gradeMapper.toDTO(grade);
    }

    @Override
    public List<GradeDTO> getAllGrades() {
        log.info("Fetching all Grades");
        return gradeRepository.findAll()
                .stream()
                .map(gradeMapper::toDTO)
                .toList();
    }
}
