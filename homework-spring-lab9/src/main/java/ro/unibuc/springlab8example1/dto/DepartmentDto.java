package ro.unibuc.springlab8example1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
        private Long departmentId;

        private String departmentName;

        private String description;
}
