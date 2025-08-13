package apps.university_app.model.mapper;

import apps.university_app.model.dto.ProgramDTO;
import apps.university_app.model.entity.Program;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgramMapper {

    @Mapping(target = "departmentId", source = "department.id")
    ProgramDTO toDto(Program program);

    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(target = "specialities", ignore = true)
    Program toEntity(ProgramDTO dto);
}
