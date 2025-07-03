package myInstagram.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import myInstagram.entities.Image;
import myInstagram.entities.Post;
import myInstagram.entities.User;
import myInstagram.service.ImageService;
import myInstagram.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final ImageService imageService;

    @GetMapping("/createPost")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        return "main/profile/createPost";
    }

//    @PostMapping("uploadPost")
//    public String uploadPost(@ModelAttribute Post post, HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        postService.save(post, user);
//        imageService.save();
//
//        return "redirect:/user/profile";
//    }

    @PostMapping("uploadPost")
    public String uploadPost(@RequestParam (name = "imageUrl") String imageUrl,
                             @RequestParam (name = "title" ) String title,
                             @RequestParam (name = "description" ) String description,
                             HttpSession session ) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }
        Post post = new Post();
        post.setTitle(title);
        post.setDescription(description);
        post.setCreatedAt(LocalDate.now());
        post.setUser(user);

        Image image = new Image();
        image.setUrl(imageUrl);
        image.setPost(post);
        if (post.getImages() == null) {
            post.setImages(new ArrayList<>());
        }
        post.getImages().add(image);
        postService.save(post);

        return "redirect:/user/profile";
    }

//    @GetMapping("/post/{id}")
//    public String showPost(@PathVariable("id") Long id, Model model) {
//        Post post = postService.getPostById(id);
//        if (post == null) {
//            return "error/404";
//        }
//
//        model.addAttribute("post", post);
//        model.addAttribute("images", post.getImages());
//        model.addAttribute("likesCount", post.getLikes() != null ? post.getLikes().size() : 0);
//        model.addAttribute("comments", post.getComments());
//        return "main/profile/showPost";
//    }


    @GetMapping("/post/{id}")
    public String showPost(@PathVariable("id") Long id, Model model) {
        Post post = postService.getPostById(id);
        if (post == null) { return "error/404"; }

        model.addAttribute("post", post);
        model.addAttribute("images", post.getImages());
        model.addAttribute("likesCount", post.getLikes() != null ? post.getLikes().size() : 0);
        model.addAttribute("comments", post.getComments());
        return "main/profile/showPost";
    }
}
