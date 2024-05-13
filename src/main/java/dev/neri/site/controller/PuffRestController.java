package dev.neri.site.controller;

import dev.neri.site.entity.Droplet;
import dev.neri.site.service.DropletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puff")
public class PuffRestController {
    @Autowired
    private DropletService dropletService;

    @GetMapping("/")
    @ResponseBody
    public List<Droplet> getAll(){
        return dropletService.findAll();
    }
}
