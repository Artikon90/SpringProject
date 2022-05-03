package classes.dao;

import classes.models.User;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/spring_project";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123456";

    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<User> getAllUsers() {
        List<User> allUserList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet allUsersSet = statement.executeQuery("SELECT * FROM alluser");
            while(allUsersSet.next()) {
                User user = new User();
                user.setId(allUsersSet.getInt("id"));
                user.setName(allUsersSet.getString("name"));
                user.setEmail(allUsersSet.getString("email"));
                user.setAge(allUsersSet.getInt("age"));
                allUserList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(allUserList);
        return allUserList;
    }

    public User getUserById(int id) {
        User user = null;
        try {
            PreparedStatement getUserSql = connection.prepareStatement(
                    "SELECT * FROM alluser WHERE id = ?");
            getUserSql.setInt(1, id);
            ResultSet res = getUserSql.executeQuery();
            user = new User();
            res.next();
            user.setId(res.getInt("id"));
            user.setName(res.getString("name"));
            user.setEmail(res.getString("email"));
            user.setAge(res.getInt("age"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addUser(User user) {
        try {
            PreparedStatement addUserSql = connection.prepareStatement(
                    "INSERT INTO alluser(name, email, age) VALUES (?, ?, ?)");
            addUserSql.setString(1, user.getName());
            addUserSql.setString(2, user.getEmail());
            addUserSql.setInt(3, user.getAge());
            addUserSql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editUser(int id, User userUpdate) {
        try {
            PreparedStatement editUserSql = connection.prepareStatement(
                    "UPDATE alluser SET name=?, email=?, age=? WHERE id=?");
            editUserSql.setString(1, userUpdate.getName());
            editUserSql.setString(2, userUpdate.getEmail());
            editUserSql.setInt(3, userUpdate.getAge());
            editUserSql.setInt(4, id);
            editUserSql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        try {
            PreparedStatement deleteUserSql = connection.prepareStatement(
                "DELETE FROM alluser WHERE id=?");
            deleteUserSql.setInt(1, id);
            deleteUserSql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
