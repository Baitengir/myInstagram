package myInstagram.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import myInstagram.entities.Post;
import myInstagram.entities.User;
import myInstagram.entities.UserInfo;
import myInstagram.service.PostService;
import myInstagram.service.UserInfoService;
import myInstagram.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PostService postService;
    private final UserInfoService userInfoService;

//    @GetMapping("/profile/{id}")
//    public String userProfile(@PathVariable("id") Long id, HttpSession session, Model model) {
//        User currentUser = (User) session.getAttribute("user");
//        if (currentUser == null) {
//            return "redirect:/auth/login";
//        }
//        User profileOwner = userService.getUserById(id);
//        if (profileOwner == null) {
//            return "error/404";
//        }
//        boolean isOwner = profileOwner.getId().equals(currentUser.getId());
//        model.addAttribute("profileOwner", profileOwner);
//        model.addAttribute("isOwner", isOwner);
//
//        return "main/profile/Profile";
//    }

    @GetMapping("/profile")
    public String userProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }
        List<Post> posts = postService.getPostsByAuthor(user.getUserName());
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        return "main/profile/Profile";
    }

    @GetMapping("/search")
    public String openSearchPage() {
        return "main/search/Search";
    }

    @GetMapping("/search/result")
    public String searchUser(@RequestParam("query") String query, Model model) {
        User user = userService.getUserByName(query);
        if (user == null) {
            model.addAttribute("error_message", "User not found");
            return "main/search/Search";
        }
        model.addAttribute("foundedUser", user);
        return "main/search/Search";
    }

    @GetMapping("/editProfile")
    public String editProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("userInfo", user.getUserInfo());
        return "main/profile/editProfile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute UserInfo userInfo, HttpSession session) {
        User user = (User) session.getAttribute("user");

        user.getUserInfo().setImage(userInfo.getImage());
        user.getUserInfo().setGender(userInfo.getGender());
        user.getUserInfo().setBiography(userInfo.getBiography());
        user.getUserInfo().setFullName(userInfo.getFullName());
        userInfoService.update(userInfo, user.getId());

        return "redirect:/user/profile";
    }
    @GetMapping("/profile/{id}")
    public String viewUserProfile(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "error/404";
        }
        List<Post> posts = postService.getPostsByAuthor(user.getUserName());
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        return "main/profile/Profile";
    }



}
