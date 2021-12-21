package ro.unibuc.springlab8example1.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.springlab8example1.domain.Points;
import ro.unibuc.springlab8example1.dto.PointsDto;

@Mapper(componentModel = "spring")
public interface PointsMapper {

    PointsDto mapToDto(Points points);
}
