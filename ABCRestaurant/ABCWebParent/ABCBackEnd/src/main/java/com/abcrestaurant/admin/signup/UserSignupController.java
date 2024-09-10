package com.abcrestaurant.admin.signup;

import com.abcrestaurant.admin.user.RoleRepository;
import com.abcrestaurant.admin.user.UserService;
import com.abcrestaurant.common.entity.Role;
import com.abcrestaurant.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserSignupController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "/customer/signup";
    }

    @PostMapping("/signup/save")
    public String saveUser(User user, Model model) {
        // Set default role as "Customer" for new signups
        Role customerRole = roleRepo.findByName("Customer");
        user.addRoles(customerRole);

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save user to the database
        userService.save(user);

        model.addAttribute("successMessage", "You have registered successfully!");
        return "/customer/login"; // Redirect to login after successful registration
    }

    @PostMapping("/perform_signup")
    public String processSignup(User user) {
        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user); // Save the user with role as 'ROLE_CUSTOMER'
        return "redirect:/login?signupSuccess";
    }
}
