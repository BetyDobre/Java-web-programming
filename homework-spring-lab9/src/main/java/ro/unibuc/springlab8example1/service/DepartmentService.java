package ro.unibuc.springlab8example1.service;

import org.springframework.stereotype.Service;
import ro.unibuc.springlab8example1.domain.Department;
import ro.unibuc.springlab8example1.dto.DepartmentDto;
import ro.unibuc.springlab8example1.mapper.DepartmentMapper;
import ro.unibuc.springlab8example1.repository.DepartmentRepository;

@Service
public class DepartmentService {
    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    public DepartmentService(DepartmentRepository repository, DepartmentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DepartmentDto addDepartment(String name) {
        Department department = Department.builder()
                .departmentName(name)
                .description("")
                .build();
        Department savedDepartment = repository.save(department);
        return mapper.mapToDto(savedDepartment);
    }

    public DepartmentDto getDepartment(String name) {
        Department department = repository.findAllByDepartmentName(name);
        return  mapper.mapToDto(department);
    }
}
