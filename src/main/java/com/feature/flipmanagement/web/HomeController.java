package com.feature.flipmanagement.web;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
     @GetMapping("/login")
    public String login() {
        return "login"; 
    }

     @GetMapping("/logoutPage")
     public String logout() {
         return "login";
     }
}
