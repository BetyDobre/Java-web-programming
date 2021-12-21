package ro.unibuc.springlab8example1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.springlab8example1.domain.Department;
import ro.unibuc.springlab8example1.dto.DepartmentDto;
import ro.unibuc.springlab8example1.service.DepartmentService;


@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService departmentService) {
        this.service = departmentService;
    }

    @PostMapping("/{name}")
    public ResponseEntity<DepartmentDto> addDepartment(@PathVariable String name) {
        return ResponseEntity.ok(service.addDepartment(name));
    }

    @GetMapping("/{name}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable String name) {
        return ResponseEntity.ok(service.getDepartment(name));
    }
}
