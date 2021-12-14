package ro.unibuc.springlab8example1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ro.unibuc.springlab8example1.domain.User;
import ro.unibuc.springlab8example1.domain.UserDetails;
import ro.unibuc.springlab8example1.domain.UserType;
import ro.unibuc.springlab8example1.dto.UserDto;
import ro.unibuc.springlab8example1.exception.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User save(User user) {
        String saveUserSql = "INSERT INTO users (username, full_name, user_type, account_created) VALUES (?,?,?,?)";
        jdbcTemplate.update(saveUserSql, user.getUsername(), user.getFullName(), user.getUserType().name(), LocalDateTime.now());

        User savedUser = getUserWith(user.getUsername());
        UserDetails userDetails = user.getUserDetails();

        if (userDetails != null) {
            String saveUserDetailsSql = "INSERT INTO user_details (cnp, age, other_information) VALUES (?, ?, ?)";
            jdbcTemplate.update(saveUserDetailsSql, userDetails.getCnp(), userDetails.getAge(), userDetails.getOtherInformation());

            UserDetails savedUserDetails = getUserDetailsWith(userDetails.getCnp());
            savedUser.setUserDetails(savedUserDetails);

            String saveUsersUserDetails = "INSERT INTO users_user_details (users, user_details) VALUES (?, ?)";
            jdbcTemplate.update(saveUsersUserDetails, savedUser.getId(), savedUserDetails.getId());
        }

        return savedUser;
    }

    public User get(String username) {
        // TODO : homework: use JOIN to fetch all details about the user
        return getAllUserWith(username);
    }

    private User getAllUserWith(String username) {
        String selectSql = "SELECT users.id, users.username, users.full_name, users.user_type, users.account_created, user_details.cnp, user_details.age, user_details.other_information " +
                "FROM users_user_details " +
                "JOIN users ON users_user_details.id = users.id " +
                "JOIN user_details ON users_user_details.id = user_details.id " +
                "WHERE users.username = ?";
        RowMapper<User> rowMapper = (resultSet, rowNo) -> User.builder()
                .id(resultSet.getLong("id"))
                .username(resultSet.getString("username"))
                .fullName(resultSet.getString("full_name"))
                .userType(UserType.valueOf(resultSet.getString("user_type")))
                .userDetails(new UserDetails(resultSet.getLong("id"), resultSet.getString("cnp"), resultSet.getInt("age"), resultSet.getString("other_information")))
                .build();

        List<User> users = jdbcTemplate.query(selectSql, rowMapper, username);

        if (!users.isEmpty()) {
            return users.get(0);
        }

        throw new UserNotFoundException("User not found");
    }

    private User getUserWith(String username) {
        String selectSql = "SELECT * from users WHERE users.username = ?";
        RowMapper<User> rowMapper = (resultSet, rowNo) -> User.builder()
                .id(resultSet.getLong("id"))
                .username(resultSet.getString("username"))
                .fullName(resultSet.getString("full_name"))
                .userType(UserType.valueOf(resultSet.getString("user_type")))
                .build();

        List<User> users = jdbcTemplate.query(selectSql, rowMapper, username);

        if (!users.isEmpty()) {
            return users.get(0);
        }

        throw new UserNotFoundException("User not found");
    }

    private UserDetails getUserDetailsWith(String cnp) {
        String selectSql = "SELECT * from user_details WHERE user_details.cnp = ?";
        RowMapper<UserDetails> rowMapper = (resultSet, rowNo) -> UserDetails.builder()
                .id(resultSet.getLong("id"))
                .cnp(resultSet.getString("cnp"))
                .age(resultSet.getInt("age"))
                .otherInformation(resultSet.getString("other_information"))
                .build();

        List<UserDetails> details = jdbcTemplate.query(selectSql, rowMapper, cnp);

        if (!details.isEmpty()) {
            return details.get(0);
        }

        throw new UserNotFoundException("User details not found");
    }

    public User update(User user, Long id) {
        String updateUserSql = "UPDATE users set username = ?, full_name = ? WHERE users.id = ?";
        jdbcTemplate.update(updateUserSql, user.getUsername(), user.getFullName(), id);

        User updatedUser = getUserWith(user.getUsername());
        UserDetails userDetails = user.getUserDetails();

        if (userDetails != null) {
            String updateUserDetailsSql = "UPDATE user_details set cnp = ?, age = ?, other_information =? WHERE user_details.id = ?";
            jdbcTemplate.update(updateUserDetailsSql, userDetails.getCnp(), userDetails.getAge(), userDetails.getOtherInformation(), id);

            UserDetails updatedUserDetails = getUserDetailsWith(userDetails.getCnp());
            updatedUser.setUserDetails(updatedUserDetails);

            String updateUsersUserDetails = "UPDATE users_user_details set users = ?, user_details = ? WHERE id = ?";
            jdbcTemplate.update(updateUsersUserDetails, updatedUser.getId(), updatedUserDetails.getId(), id);
        }

        return updatedUser;
    }

    public String delete(Long id) {
        String deleteSql = "DELETE from users WHERE users.id = ?";
        jdbcTemplate.update(deleteSql, id);
        return ("Deleted user with id " + id);
    }

    public List<User> getUsersFilteredByType(String type) {
        String selectSql = "SELECT * from users WHERE users.user_type = ?";
        RowMapper<User> rowMapper = (resultSet, rowNo) -> User.builder()
                .id(resultSet.getLong("id"))
                .username(resultSet.getString("username"))
                .fullName(resultSet.getString("full_name"))
                .userType(UserType.valueOf(resultSet.getString("user_type")))
                .build();

        List<User> users = jdbcTemplate.query(selectSql, rowMapper, type.toUpperCase());

        if (!users.isEmpty()) {
            return users;
        }
        throw new UserNotFoundException("Users of this type can't be found");

    }
}
