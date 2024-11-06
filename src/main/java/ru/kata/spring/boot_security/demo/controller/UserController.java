package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/users/add")
    public String addUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit")
    public String editUserForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/users/update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/delete")
    public String deleteUserForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "delete-user";
    }

    @PostMapping("/users/delete")
    public String deleteUser(Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
