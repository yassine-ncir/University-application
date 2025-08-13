package apps.university_app.model.mapper;

import apps.university_app.model.dto.DepartmentDTO;
import apps.university_app.model.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDTO toDTO(Department department);

    Department toEntity(DepartmentDTO dto);
}
