package apps.university_app.model.mapper;

import apps.university_app.model.dto.CourseDTO;
import apps.university_app.model.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(target = "gradeId", source = "grade.id")
    CourseDTO toDTO(Course course);

    Course toEntity(CourseDTO dto);
}
