package dev.neri.site.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simplefile")
public class SimpleFileTransferRestController {
    @GetMapping("/ping")
    public @ResponseBody String getPingMessage(){
        return "Pong";
    }
}
