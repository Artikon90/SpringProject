package classes.services;

import classes.models.User;
import classes.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findById(int id) {
        Optional<User> findUser = userRepo.findById(id);
        return findUser.orElse(null);
    }
    @Transactional
    public void editUser(int id, User userUpdate) {
        userUpdate.setId(id);
        userRepo.save(userUpdate);
    }
    @Transactional
    public void addNewUser(User user) {
        userRepo.save(user);
    }
    @Transactional
    public void delete(int id) {
        userRepo.deleteById(id);
    }
}
