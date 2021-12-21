package ro.unibuc.springlab8example1.service;

import org.springframework.stereotype.Service;
import ro.unibuc.springlab8example1.domain.Points;
import ro.unibuc.springlab8example1.dto.PointsDto;
import ro.unibuc.springlab8example1.mapper.PointsMapper;
import ro.unibuc.springlab8example1.repository.PointsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointService {

    private final PointsRepository repository;
    private final PointsMapper mapper;

    public PointService(PointsRepository repository, PointsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PointsDto addPointsFor(String username) {
        Points points = Points.builder()
                .username(username)
                .noPoints(10)
                .pointsAddedDate(LocalDateTime.now())
                .build();
        Points savedPoints = repository.save(points);
        return mapper.mapToDto(savedPoints);
    }

    public List<PointsDto> getPointsFor(String username) {
        List<Points> points = repository.findAllByUsername(username);

        return points.stream().map(p -> mapper.mapToDto(p)).collect(Collectors.toList());
    }
}
