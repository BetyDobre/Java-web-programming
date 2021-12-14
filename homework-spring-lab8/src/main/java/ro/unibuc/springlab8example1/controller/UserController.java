package ro.unibuc.springlab8example1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.springlab8example1.domain.UserType;
import ro.unibuc.springlab8example1.dto.UserDto;
import ro.unibuc.springlab8example1.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/student")
    public ResponseEntity<UserDto> createStudent(@RequestBody UserDto userDto) {
        return ResponseEntity
                .ok()
                .body(userService.create(userDto, UserType.STUDENT));
    }

    @PostMapping("/professor")
    public ResponseEntity<UserDto> createProfessor(@RequestBody UserDto userDto) {
        return ResponseEntity
                .ok()
                .body(userService.create(userDto, UserType.PROFESSOR));
    }

    @PostMapping("/admin")
    public ResponseEntity<UserDto> createAdmin(@RequestBody UserDto userDto) {
        return ResponseEntity
                .ok()
                .body(userService.create(userDto, UserType.ADMIN));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> get(@PathVariable String username) {
        return ResponseEntity
                .ok()
                .body(userService.getOne(username));
    }

    // TODO: homework: endpoints for updating a user, deleting one, get all users filtered by type

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto, @PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(userService.update(userDto, id));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return (userService.deleteOne(id));
    }

    @GetMapping("/filter/{type}")
    public ResponseEntity<List<UserDto>> getUsersFilteredByType(@PathVariable String type){
        return ResponseEntity
                .ok()
                .body(userService.getUsersFilteredByType(type));
    }
}
