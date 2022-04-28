package classes.controller;

import classes.dao.UserDAO;
import classes.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserControllers {
    private final UserDAO userDAO;
    @Autowired
    UserControllers(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
@GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("allUserData", userDAO.getAllUsers());
        return "user/allUserPage";
    }
@GetMapping("/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("userById", userDAO.getUserById(id));
        return "user/userPage";
    }
@GetMapping("/add")
    public String addNewUser(Model model) {
        model.addAttribute("userToAdd", new User());
        return "user/newUser";
    }
@PostMapping()
    public String addUser(@ModelAttribute("userToAdd") User user) {
        userDAO.addUser(user);
        return "redirect:/users";
    }
@GetMapping("/{id}/edit")
    public String editUserView(Model model, @PathVariable("id") int id) {
        model.addAttribute("userToEdit", userDAO.getUserById(id));
        return "user/editUserPage";
    }
@PatchMapping("/{id}")
    public String editUser(@ModelAttribute("userToEdit") User user, @PathVariable("id") int id) {
        userDAO.editUser(id, user);
        return "redirect:/users";
    }
@DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userDAO.deleteUser(id);
        return "redirect:/users";
    }
}
