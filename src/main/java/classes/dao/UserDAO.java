package classes.dao;

import classes.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM alluser", new BeanPropertyRowMapper<>(User.class));
    }

    public User getUserById(int id) {
        return jdbcTemplate.query("SELECT * FROM alluser WHERE id=?",
                new BeanPropertyRowMapper<>(User.class), id).stream().findAny().orElse(null);
    }

    public Optional<User> getUserByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM alluser WHERE email=?",
                new BeanPropertyRowMapper<>(User.class), email).stream().findAny();
    }

    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO alluser(name, email, age) VALUES(?, ?, ?)",
                user.getName(), user.getEmail(), user.getAge());
    }

    public void editUser(int id, User userUpdate) {
        jdbcTemplate.update("UPDATE alluser SET name = ?, email = ?, age = ? WHERE id = ?",
                userUpdate.getName(), userUpdate.getEmail(), userUpdate.getAge(), id);
    }

    public void deleteUser(int id) {
        jdbcTemplate.update("DELETE FROM alluser WHERE id = ?", id);
    }
}
