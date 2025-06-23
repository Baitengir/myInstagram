package myInstagram.controllers;

import lombok.RequiredArgsConstructor;
import myInstagram.entities.User;
import myInstagram.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private static final Log log = LogFactory.getLog(AuthController.class);
    private final UserService userService;

    @GetMapping
    public String authUser() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @GetMapping("/checkUser")
    public String checkUser(@RequestParam String loginInput,
                            @RequestParam String password) {
        if (loginInput.matches("^\\+996\\d{9}$")) {
            User user = userService.getUserByPhone(loginInput);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return "main/profile";
                } else {
                    return "auth/register"; // если user ввел неправильный пароль
                }
            } else {
                return "auth/register"; // если user null
            }

        } else if (loginInput.contains("@") && loginInput.contains(".")) {
            User user = userService.getUserByEmail(loginInput);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return "main/profile";
                } else {
                    return "auth/register";
                }
            } else {
                return "auth/register";
            }

        } else {
            User user = userService.getUserByName(loginInput);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return "main/profile";
                } else {
                    return "auth/register";
                }
            } else {
                return "auth/register";
            }
        }
    }

    @PostMapping("/saveUser")
    public String save(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/mainPage/mainPanel";
    }

}

