package doston.uz.App.controller;

import doston.uz.App.model.enums.LessonTime;
import doston.uz.App.model.enums.Level;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {


        return "tracker/index";
    }

    // Hech qaysi men o'rgangan principlarga to'g'ri kelmaydi hammasini violate qiladi shunga o'zgartirilishi kerak
    @GetMapping
    @RequestMapping("api/v1/")
    public String redirectToHome() {
        return "redirect:/";
    }


}
