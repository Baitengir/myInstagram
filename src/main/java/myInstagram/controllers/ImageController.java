package myInstagram.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import myInstagram.entities.Image;
import myInstagram.entities.User;
import myInstagram.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    public void saveImage(Image image, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        image.setUser(user);

        imageService.save(image);
    }
}
