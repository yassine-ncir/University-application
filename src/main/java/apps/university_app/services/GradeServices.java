package apps.university_app.services;

import apps.university_app.model.dto.GradeDTO;

import java.util.List;

public interface GradeServices {
    GradeDTO createGrade(GradeDTO dto);
    GradeDTO updateGrade(Long id, GradeDTO dto);
    void deleteGrade(Long id);
    GradeDTO getGradeById(Long id);
    List<GradeDTO> getAllGrades();
}
