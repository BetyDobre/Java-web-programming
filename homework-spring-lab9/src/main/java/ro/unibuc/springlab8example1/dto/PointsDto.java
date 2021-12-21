package ro.unibuc.springlab8example1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointsDto {

    private Long id;

    private String username;

    private Integer noPoints;

    private LocalDateTime pointsAddedDate;
}
