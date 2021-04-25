package com.portfolio.toy.controller.notice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/notice")
@Controller
public class NoticeController {

    @GetMapping("/list")
    public void list() {
        log.info("list : access to all");
    }

    @GetMapping("/register")
    public void registerForm() {
        log.info("registerForm : access to admin");
    }
}
