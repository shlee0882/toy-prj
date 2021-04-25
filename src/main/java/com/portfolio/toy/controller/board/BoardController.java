package com.portfolio.toy.controller.board;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/board")
@Controller
public class BoardController {

    @GetMapping("/list")
    public void list() {
        log.info("list : access to all");
    }

    @GetMapping("/register")
    public void registerForm() {
        log.info("registerForm : access to member");
    }
}
