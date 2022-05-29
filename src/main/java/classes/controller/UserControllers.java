package classes.controller;

import classes.dao.UserDAO;
import classes.models.User;
import classes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserControllers {

    private final UserService userService;

    @Autowired
    UserControllers(UserDAO userDAO, UserService userService) {
        this.userService = userService;
    }
@GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("allUserData", userService.findAll());
        return "user/allUserPage";
    }
@GetMapping("/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("userById", userService.findById(id));
        return "user/userPage";
    }
@GetMapping("/add")
    public String addNewUser(@ModelAttribute("userToAdd") User user) {
        return "user/newUser";
    }
@PostMapping()
public String addUser(@Valid @ModelAttribute("userToAdd") User user,
                      BindingResult bindingResult) {
    if(bindingResult.hasErrors())
        return "user/newUser";

    userService.addNewUser(user);
    return "redirect:/users";
}
@GetMapping("/{id}/edit")
    public String editUserPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("userToEdit", userService.findById(id));
        return "user/editUserPage";
    }
@PatchMapping("/{id}")
    public String editUser(@Valid @ModelAttribute("userToEdit") User user, BindingResult bindingResult,
                       @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "user/editUserPage";

        userService.editUser(id, user);
        return "redirect:/users";
    }
@DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
