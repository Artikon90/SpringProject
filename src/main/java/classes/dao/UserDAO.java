package classes.dao;

import classes.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private List<User> allUsers;
    private static int userCounter;

    {
        allUsers = new ArrayList<>();
        allUsers.add(new User(++userCounter, "Jack"));
        allUsers.add(new User(++userCounter, "Sasha"));
        allUsers.add(new User(++userCounter, "Glenn"));
        allUsers.add(new User(++userCounter, "Mark"));
        allUsers.add(new User(++userCounter, "Mary"));
        allUsers.add(new User(++userCounter, "Alex"));
        allUsers.add(new User(++userCounter, "Max"));
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public User getUserById(int id) {
        return allUsers.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void addUser(User user) {
        user.setId(++userCounter);
        allUsers.add(user);
    }
}
