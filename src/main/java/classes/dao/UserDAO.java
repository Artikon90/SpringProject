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
        allUsers.add(new User(++userCounter, "Jack", "aboba@mail.com", 24));
        allUsers.add(new User(++userCounter, "Sasha", "google@google.com", 43));
        allUsers.add(new User(++userCounter, "Glenn", "glenn123@noob.org", 28));
        allUsers.add(new User(++userCounter, "Mark", "marksman@yahoo.com", 32));
        allUsers.add(new User(++userCounter, "Mary", "todotodo@test.jp", 10));
        allUsers.add(new User(++userCounter, "Alex", "oldman@rambler.org", 64));
        allUsers.add(new User(++userCounter, "Max", "maximus@google.nl", 31));
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

    public void editUser(int id, User user) {
        User userToEdit = getUserById(id);
        userToEdit.setName(user.getName());
        userToEdit.setAge(user.getAge());
        userToEdit.setEmail(user.getEmail());
    }

    public void deleteUser(int id) {
        allUsers.removeIf(p -> p.getId() == id);
    }
}
