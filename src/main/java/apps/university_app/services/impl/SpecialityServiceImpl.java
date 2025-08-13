package apps.university_app.services.impl;

import apps.university_app.model.dto.SpecialityDTO;
import apps.university_app.model.entity.Program;
import apps.university_app.model.entity.Speciality;
import apps.university_app.model.mapper.SpecialityMapper;
import apps.university_app.repository.ProgramRepo;
import apps.university_app.repository.SpecialityRepo;
import apps.university_app.services.SpecialityServices;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static apps.university_app.model.enums.DegreeProgType.DOCTORATE;
import static apps.university_app.model.enums.DegreeProgType.MASTER;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpecialityServiceImpl implements SpecialityServices {

    private final SpecialityRepo specialityRepository;
    private final ProgramRepo programRepository;
    private final SpecialityMapper specialityMapper;

    @Override
    public SpecialityDTO createSpeciality(SpecialityDTO dto) {
        log.info("Creating a new speciality");
        Speciality speciality = specialityMapper.toEntity(dto);

        Program program = programRepository.findById(dto.getProgramId())
                .orElseThrow(() -> {
                    log.error("Program not found for the given program ID during speciality creation");
                    return new EntityNotFoundException("Program of the speciality not found");
                });

        speciality.setProgram(program);
        setDurationBasedOnProgram(speciality);
        speciality.setId(null);
        Speciality saved = specialityRepository.save(speciality);
        log.info("Speciality created successfully");
        return specialityMapper.toDTO(saved);
    }

    @Override
    public SpecialityDTO updateSpeciality(Long id, SpecialityDTO dto) {
        log.info("Updating a speciality");
        Speciality existing = specialityRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Speciality not found for the given ID during update");
                    return new EntityNotFoundException("Speciality not found");
                });

        if (dto.getName() != null && !dto.getName().isEmpty()) {
            existing.setName(dto.getName());
        }

        if (dto.getDescription() != null && !dto.getDescription().isEmpty()) {
            existing.setDescription(dto.getDescription());
        }

        if (dto.getAvailable() != null) {
            existing.setAvailable(dto.getAvailable());
        }

        Program program = programRepository.findById(dto.getProgramId())
                .orElseThrow(() -> {
                    log.error("Program not found for the given program ID during speciality update");
                    return new EntityNotFoundException("Program id of speciality not found");
                });
        existing.setProgram(program);

        setDurationBasedOnProgram(existing);

        specialityRepository.save(existing);
        log.info("Speciality updated successfully");
        return specialityMapper.toDTO(existing);
    }

    @Override
    public void deleteSpeciality(Long id) {
        log.info("Deleting a speciality");
        if (!specialityRepository.existsById(id)) {
            log.error("Speciality not found for the given ID during deletion");
            throw new EntityNotFoundException("Speciality not found");
        }
        specialityRepository.deleteById(id);
        log.info("Speciality deleted successfully");
    }

    @Override
    public SpecialityDTO getSpecialityById(Long id) {
        log.info("Fetching a speciality");
        Speciality speciality = specialityRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Speciality not found for the given ID during fetch");
                    return new EntityNotFoundException("Speciality not found");
                });
        return specialityMapper.toDTO(speciality);
    }

    @Override
    public List<SpecialityDTO> getAllSpecialities() {
        log.info("Fetching all specialities");
        return specialityRepository.findAll()
                .stream()
                .map(specialityMapper::toDTO)
                .toList();
    }

    public void setDurationBasedOnProgram(Speciality speciality) {
        if (speciality.getProgram() != null && speciality.getProgram().getType() != null) {
            switch (speciality.getProgram().getType()) {
                case LICENCE -> {
                    speciality.setDuration(3);
                    log.debug("Duration set based on LICENCE program type");
                }
                case MASTER -> {
                    speciality.setDuration(2);
                    log.debug("Duration set based on MASTER program type");
                }
                case DOCTORATE -> {
                    speciality.setDuration(4);
                    log.debug("Duration set based on DOCTORATE program type");
                }
                default -> {
                    speciality.setDuration(0);
                    log.debug("Duration set to default (0) due to unknown program type");
                }
            }
        } else {
            log.warn("Program or program type is null, cannot set duration");
        }
    }
}
