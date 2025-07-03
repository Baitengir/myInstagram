package myInstagram.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import myInstagram.entities.Follower;
import myInstagram.entities.User;
import myInstagram.entities.UserInfo;
import myInstagram.service.UserInfoService;
import myInstagram.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private static final Log log = LogFactory.getLog(AuthController.class);
    private final UserService userService;
    private final UserInfoService userInfoService;

    @GetMapping
    public String authUser() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/checkUser") // todo login
    public String checkUser(@RequestParam("loginInput") String loginInput,
                            @RequestParam("password") String password, Model model,
                            HttpSession session) {

        if (loginInput.matches("^\\+996\\d{9}$")) {
            User user = userService.getUserByPhone(loginInput);
            if (user != null && user.getPassword().equals(password)) {
                    model.addAttribute("user", user);
                    session.setAttribute("user", user);
                    return "main/home/Home";
            } else {
                return "auth/register";
            }

        } else if (loginInput.contains("@") && loginInput.contains(".")) {
            User user = userService.getUserByEmail(loginInput);
            if (user != null && user.getPassword().equals(password)) {
                model.addAttribute("user", user);
                session.setAttribute("user", user);
                return "main/home/Home";
            } else {
                return "auth/register";
            }
        } else {
            User user = userService.getUserByName(loginInput);
            if (user != null && user.getPassword().equals(password)) {
                model.addAttribute("user", user);
                session.setAttribute("user", user);
                return "main/home/Home";
            } else {
                return "auth/register";
            }
        }
    }

    @PostMapping("/saveUser")
    public String save(@ModelAttribute("user") User user, HttpSession session, Model model) {
        UserInfo userInfo = new UserInfo();
        Follower follower = new Follower();
        follower.setSubscribers(new ArrayList<>());
        follower.setSubscriptions(new ArrayList<>());
        user.setPosts(new ArrayList<>());
        user.setUserInfo(userInfo);
        user.setFollower(follower);

        User userByName = userService.getUserByName(user.getUserName());
        if (userByName != null) {
            model.addAttribute("errorMessageExistUserName", "Username " + userByName.getUserName() + " is already taken");
            return "auth/register";
        }
        User userByPhone = userService.getUserByPhone(user.getPhone());
        if (userByPhone != null){
            model.addAttribute("errorMessageExistPhone", "Phone " + userByPhone.getPhone() + " is already using");
            return "auth/register";
        }
        User userByEmail = userService.getUserByEmail(user.getEmail());
        if (userByEmail != null){
            model.addAttribute("errorMessageExistEmail", "Email " + userByEmail.getEmail() + " is already using");
            return "auth/register";
        }

        userService.save(user);
        session.setAttribute("user", user);
        return "redirect:/mainPage/mainPanel";
    }

}

