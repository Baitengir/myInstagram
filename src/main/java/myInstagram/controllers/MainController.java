package myInstagram.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import myInstagram.entities.Post;
import myInstagram.entities.User;
import myInstagram.service.FollowerService;
import myInstagram.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mainPage")
@RequiredArgsConstructor
public class MainController {
    private final FollowerService followerService;
    private final PostService postService;

    @GetMapping("mainPanel")
    public String mainPanel(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<User> subscriptionUsers = followerService.getSubscribersOrSubscriptionsByFollowerId(user.getId(), "subscriptions");
        List<Post> posts = new ArrayList<>();
//        for (User subscriptionUser : subscriptionUsers) {
//            posts.add(subscriptionUser.getPosts())
//        }

        model.addAttribute("subscriptions", followerService.getSubscribersOrSubscriptionsByFollowerId(user.getId(),"subscriptions"));

        model.addAttribute("user", user);
        return "main/home/Home";
    }


}
