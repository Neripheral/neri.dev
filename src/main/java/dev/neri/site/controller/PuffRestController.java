package dev.neri.site.controller;

import dev.neri.site.entity.Droplet;
import dev.neri.site.service.DropletService;
import dev.neri.site.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/puff/api")
@CrossOrigin(origins="*")
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
                        MvcUriComponentsBuilder.fromMethodCall(on(PuffRestController.class).getFile(newDroplet.getId())).toUriString())
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Location")
                .body(newDroplet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable("id") Integer id){
        Resource theFile = storageService.load(id);
        String theFilename = dropletService.findById(id).getFilename();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(theFilename).build().toString())
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Content-Disposition")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(theFile);
    }
}
