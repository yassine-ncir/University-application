package apps.university_app.model.mapper;

import apps.university_app.model.dto.GradeDTO;
import apps.university_app.model.entity.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GradeMapper {
    @Mapping(target = "speciality_id", source = "speciality.id")
    GradeDTO toDTO(Grade grade);

    @Mapping(target = "speciality", ignore = true)
    Grade toEntity(GradeDTO dto);
}