package dev.neri.site.controller;

import dev.neri.site.entity.Droplet;
import dev.neri.site.service.DropletService;
import dev.neri.site.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/puff")
public class PuffRestController {
    @Autowired
    private DropletService dropletService;

    @Autowired
    private StorageService storageService;

    @GetMapping("/")
    @ResponseBody
    public List<Droplet> getAll(){
        return dropletService.findAll();
    }

    @PostMapping("/")
    @Transactional
    public ResponseEntity<Droplet> saveFile(@RequestParam("file") MultipartFile file){
        Droplet newDroplet = new Droplet(file.getOriginalFilename());
        newDroplet = dropletService.saveDroplet(newDroplet);

        storageService.store(file, newDroplet.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(
                        HttpHeaders.LOCATION,
                        MvcUriComponentsBuilder.fromMethodCall(on(PuffRestController.class).getAll()).toUriString())
                .body(newDroplet);
    }
}
