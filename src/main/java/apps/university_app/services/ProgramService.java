package apps.university_app.services;


import apps.university_app.model.dto.ProgramDTO;

import java.util.List;

public interface ProgramService {
    void createProgram(ProgramDTO programDTO);
    ProgramDTO updateProgram(Long id, ProgramDTO programDTO);
    void deleteProgram(Long id);
    ProgramDTO getProgramById(Long id);
    List<ProgramDTO> getAllPrograms();
}
