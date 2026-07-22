package com.smartbus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/addBus")
    public String addBus() {
        return "addBus";
}
}