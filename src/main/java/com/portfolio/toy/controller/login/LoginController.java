package com.portfolio.toy.controller.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class LoginController {
    // 에러 메시지와 로그아웃 메시지를 파라미터로 사용
    @RequestMapping("/login")
    public String loginForm(String error, String logout, Model model) {
        log.info("error: " + error);
        log.info("logout: " + logout);
        if (error != null) {
            model.addAttribute("error", "Login Error!!!");
        }
        if (logout != null) {
            model.addAttribute("logout", "Logout!!!");
        }
        return "/login/loginForm";
    }

    @GetMapping("/logout")
    public String logoutForm() {
        log.info("logoutForm");
        return "/login/logoutForm";
    }
}