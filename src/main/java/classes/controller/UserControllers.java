package classes.controller;

import classes.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
