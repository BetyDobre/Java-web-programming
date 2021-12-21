package ro.unibuc.springlab8example1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.unibuc.springlab8example1.domain.Department;
import ro.unibuc.springlab8example1.domain.User;
import ro.unibuc.springlab8example1.domain.UserType;
import ro.unibuc.springlab8example1.dto.UserDto;
import ro.unibuc.springlab8example1.exception.DepartmentNotFoundException;
import ro.unibuc.springlab8example1.mapper.UserMapper;
import ro.unibuc.springlab8example1.repository.DepartmentRepository;
import ro.unibuc.springlab8example1.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserMapper userMapper;

    // refactored create method to check for Department
    public UserDto create(UserDto userDto, UserType type, Long departmentId) {
        User user = userMapper.mapToEntity(userDto);

        Optional<Department> departmentOpt = Optional.ofNullable(departmentRepository.findDepartmentByDepartmentId(departmentId));
        if (departmentOpt.isPresent()) {
            user.setDepartment(departmentOpt.get());
            departmentOpt.get().getUsers().add(user);
            user.setUserType(type);
            user.setAccountCreated(LocalDateTime.now());
            User savedUser = userRepository.save(user);

            return userMapper.mapToDto(savedUser);
        } else {
            throw new DepartmentNotFoundException("DepartmentId " + departmentId+ " not found");
        }

    }

    public UserDto getOne(String username) {
        return userMapper.mapToDto(userRepository.findByUsername(username));
    }

    public List<UserDto> getFilteredUsers(String name, UserType userType) {
        List<User> users = userRepository.filter(userType, name);

        return users.stream().map(u -> userMapper.mapToDto(u)).collect(Collectors.toList());
    }
}
