package com.abcrestaurant.admin.signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "customer/login"; // Return login.html (Thymeleaf template)
    }
}
