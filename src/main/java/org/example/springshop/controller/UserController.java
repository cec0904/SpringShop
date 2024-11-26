package org.example.springshop.controller;

import org.example.springshop.domain.User;
import org.example.springshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("member", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.registerMember(user);
        return "redirect:/login";
    }
}
