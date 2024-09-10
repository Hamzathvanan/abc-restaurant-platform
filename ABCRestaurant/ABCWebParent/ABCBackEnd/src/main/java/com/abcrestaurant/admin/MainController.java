package com.abcrestaurant.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/admin-home")
    public String viewHomePage() {
        return "index";
    }
}
