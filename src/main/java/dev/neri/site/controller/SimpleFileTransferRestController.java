package dev.neri.site.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/simplefile")
public class SimpleFileTransferRestController {
    @GetMapping("/ping")
    public @ResponseBody String getPingMessage(){
        return "Pong";
    }

    @GetMapping(
            value = "/image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public void getImage(HttpServletResponse response) {
        response.setHeader("Content-Type", MediaType.IMAGE_JPEG_VALUE);
        String fullFileName = "/static/image.jpg";
        try (InputStream in = getClass().getResourceAsStream(fullFileName)) {
            if(in == null) throw new IOException("Could not find the resource with name " + fullFileName);

            IOUtils.copy(in, response.getOutputStream());
            response.flushBuffer();
        } catch(IOException e){
            throw new RuntimeException("Could not return the image.");
        }
    }
}
