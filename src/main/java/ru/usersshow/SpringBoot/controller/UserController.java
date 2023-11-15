package ru.usersshow.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.usersshow.SpringBoot.model.User;
import ru.usersshow.SpringBoot.service.UserService;

import javax.validation.Valid;
import java.util.List;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String getAllUser(Model model) {
        List<User> users = userService.getListUser();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "users/")
    public String getUser(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", userService.readUser(id));
        return "showUser";
    }

    @GetMapping(value = "users/new")
    public String getNewUser(Model model) {
        model.addAttribute(new User());
        return "new";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        userService.createUser(user);
        return "redirect:/";
    }

    @GetMapping("users/edit/")
    public String getUpdateUser(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", userService.readUser(id));
        return "edit";
    }

    @PostMapping("users/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userService.updateUser(user);
        return "redirect:/";
    }

    @PostMapping("users/delete/")
    public String deleteUser(@RequestParam("id") long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

}
