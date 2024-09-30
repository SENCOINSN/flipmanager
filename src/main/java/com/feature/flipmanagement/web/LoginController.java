package com.feature.flipmanagement.web;

import com.feature.flipmanagement.dto.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String login(Model model) {
        LoginRequest loginRequest = new LoginRequest();
        model.addAttribute("loginRequest", loginRequest);
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginRequest loginRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "login";
        }
        if(loginRequest.getUsername().equals("admin") && loginRequest.getPassword().equals("admin")) {
            return "redirect:/feature/console";
        }
        return "login";
    }
}
