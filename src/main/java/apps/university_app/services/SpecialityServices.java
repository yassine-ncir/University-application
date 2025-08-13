package apps.university_app.services;

import apps.university_app.model.dto.SpecialityDTO;
import apps.university_app.model.entity.Speciality;

import java.util.List;

public interface SpecialityServices {
    SpecialityDTO createSpeciality(SpecialityDTO dto);
    SpecialityDTO updateSpeciality(Long id, SpecialityDTO dto);
    void deleteSpeciality(Long id);
    SpecialityDTO getSpecialityById(Long id);
    List<SpecialityDTO> getAllSpecialities();
    void setDurationBasedOnProgram(Speciality speciality);
}
