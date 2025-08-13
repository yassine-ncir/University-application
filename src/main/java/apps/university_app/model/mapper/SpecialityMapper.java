package apps.university_app.model.mapper;

import apps.university_app.model.dto.SpecialityDTO;
import apps.university_app.model.entity.Speciality;
import lombok.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpecialityMapper {

    @Mapping(target = "programId", source = "program.id")
    SpecialityDTO toDTO(Speciality speciality);

    Speciality toEntity(SpecialityDTO dto);
}
