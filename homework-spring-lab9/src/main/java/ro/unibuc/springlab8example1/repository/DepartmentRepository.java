package ro.unibuc.springlab8example1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.unibuc.springlab8example1.domain.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findAllByDepartmentName(String departmentName);
    Department findDepartmentByDepartmentId(Long departmentId);
}
