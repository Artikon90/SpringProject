package classes.dao;

import classes.models.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static int userCounter;
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
        return allUserList;
    }

    public User getUserById(int id) {
        return null;
//        return allUsers.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void addUser(User user) {
//        user.setId(++userCounter);
//        allUsers.add(user);
    }

    public void editUser(int id, User user) {
        User userToEdit = getUserById(id);
        userToEdit.setName(user.getName());
        userToEdit.setAge(user.getAge());
        userToEdit.setEmail(user.getEmail());
    }

    public void deleteUser(int id) {
//        allUsers.removeIf(p -> p.getId() == id);
    }
}
