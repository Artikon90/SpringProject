package classes.controller;

import classes.dao.UserDAO;
import classes.models.User;
import classes.util.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserControllers {
    private final UserDAO userDAO;
    private final UserValidator userValidator;
    @Autowired
    UserControllers(UserDAO userDAO, UserValidator userValidator) {
        this.userDAO = userDAO;
        this.userValidator = userValidator;
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
public String addUser(@Valid @ModelAttribute("userToAdd") User user,
                      BindingResult bindingResult) {
    userValidator.validate(user, bindingResult);
    if(bindingResult.hasErrors())
        return "user/newUser";

    userDAO.addUser(user);
    return "redirect:/users";
}
@GetMapping("/{id}/edit")
    public String editUserPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("userToEdit", userDAO.getUserById(id));
        return "user/editUserPage";
    }
@PatchMapping("/{id}")
    public String editUser(@Valid @ModelAttribute("userToEdit") User user, BindingResult bindingResult,
                       @PathVariable("id") int id) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "user/editUserPage";

        userDAO.editUser(id, user);
        return "redirect:/users";
    }
@DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userDAO.deleteUser(id);
        return "redirect:/users";
    }
}
