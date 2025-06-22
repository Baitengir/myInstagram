package myInstagram.controllers;

import myInstagram.entities.User;
import myInstagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String authUser (){
        return "auth/login";
    }

    @GetMapping("register")
    public String registerUser(Model model){
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("save")
    public String save (@ModelAttribute("user") User user){
        userService.save(user);

        return "redirect:/";
    }
}
