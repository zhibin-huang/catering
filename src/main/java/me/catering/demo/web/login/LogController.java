package me.catering.demo.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class LogController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin(){
        return "redirect:/admin/shops";

    }
    @GetMapping("/logged_in")
    @ResponseBody
    public boolean checkStatus(Principal principal) {
        return principal != null;
    }
}
