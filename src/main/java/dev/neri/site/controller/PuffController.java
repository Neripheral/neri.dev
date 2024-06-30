package dev.neri.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/puff")
public class PuffController {
    @GetMapping("/")
    public String getHomepage(){
        return "homepage";
    }
}
