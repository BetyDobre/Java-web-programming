package ro.unibuc.springlab8example1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.unibuc.springlab8example1.dto.PointsDto;
import ro.unibuc.springlab8example1.service.PointService;

import java.util.List;

@RestController
@RequestMapping("/points")
public class PointController {

    private final PointService service;

    public PointController(PointService service) {
        this.service = service;
    }

    @PostMapping("/{username}")
    public ResponseEntity<PointsDto> addPoints(@PathVariable String username) {
        return ResponseEntity.ok(service.addPointsFor(username));
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<PointsDto>> getPoints(@PathVariable String username) {
        return ResponseEntity.ok(service.getPointsFor(username));
    }
}
