package ro.unibuc.springlab8example1.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.springlab8example1.domain.Department;
import ro.unibuc.springlab8example1.dto.DepartmentDto;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDto mapToDto(Department department);
}
