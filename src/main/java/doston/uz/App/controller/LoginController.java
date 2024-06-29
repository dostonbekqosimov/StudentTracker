package doston.uz.App.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/")
public class LoginController {

    @GetMapping("login")
    public String getLoginPage(){
        return "tracker/fragments/login";
    }
}
