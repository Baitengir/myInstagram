package myInstagram.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mainPage")
public class MainController {
    @GetMapping("mainPanel")
    public String mainPanel(){
        return "main/profile";
    }


}
