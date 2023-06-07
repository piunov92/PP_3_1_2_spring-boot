package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/add")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "createAndUpdateUser";
    }

    @PostMapping("/create-and-update-user")
    public String userSubmit(@ModelAttribute("user") User user) {
        if (userService.getUserById(user.getId()) == null) {
            userService.saveUser(user);
        } else {
            userService.updateUser(user);
        }
        return "redirect:/";
    }

    @PatchMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "createAndUpdateUser";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
