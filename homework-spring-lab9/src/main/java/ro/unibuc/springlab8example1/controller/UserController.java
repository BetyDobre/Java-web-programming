package ro.unibuc.springlab8example1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.unibuc.springlab8example1.domain.UserType;
import ro.unibuc.springlab8example1.dto.UserDto;
import ro.unibuc.springlab8example1.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // refactored Post methods to validate if the user is in an existing department
    @PostMapping("/student/{departmentId}")
    public ResponseEntity<UserDto> createStudent(@RequestBody UserDto userDto, @PathVariable Long departmentId) {
        return ResponseEntity
                .ok()
                .body(userService.create(userDto, UserType.STUDENT, departmentId));
    }

    @PostMapping("/professor/{departmentId}")
    public ResponseEntity<UserDto> createProfessor(@RequestBody UserDto userDto, @PathVariable Long departmentId) {
        return ResponseEntity
                .ok()
                .body(userService.create(userDto, UserType.PROFESSOR, departmentId));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> get(@PathVariable String username) {
        return ResponseEntity
                .ok()
                .body(userService.getOne(username));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<UserDto>> getFilteredUsers(@RequestParam String name,
                                                          @RequestParam String userType) {
        return ResponseEntity
                .ok()
                .body(userService.getFilteredUsers(name, UserType.valueOf(userType)));
    }

}
