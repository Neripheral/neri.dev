package dev.neri.site.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/document")
    public void getDocument(HttpServletResponse response) {
        response.setHeader("Content-Type", MediaType.APPLICATION_PDF_VALUE);
        String fullFileName = "/static/document.pdf";
        try (InputStream in = getClass().getResourceAsStream(fullFileName)) {
            if(in == null) throw new IOException("Could not find the resource with name " + fullFileName);

            IOUtils.copy(in, response.getOutputStream());
            response.flushBuffer();
        } catch(IOException e){
            throw new RuntimeException("Could not return the document.");
        }
    }

    @GetMapping("/archive")
    public void getArchive(HttpServletResponse response) {
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=archive.zip");
        String fullFileName = "/static/archive.zip";
        try (InputStream in = getClass().getResourceAsStream(fullFileName)) {
            if(in == null) throw new IOException("Could not find the resource with name " + fullFileName);

            IOUtils.copy(in, response.getOutputStream());
            response.flushBuffer();
        } catch(IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/archive/{filename}")
    public void getArchiveWithCustomName(@PathVariable("filename") String filename, HttpServletResponse response){
        response.setContentType("application/zip");

        ContentDisposition contentDisposition =
                ContentDisposition
                        .builder("attachment")
                        .filename(filename + ".zip")
                        .build();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());

        String fullFileName = "/static/archive.zip";
        try (InputStream in = getClass().getResourceAsStream(fullFileName)) {
            if(in == null) throw new IOException("Could not find the resource with name " + fullFileName);

            IOUtils.copy(in, response.getOutputStream());
            response.flushBuffer();
        } catch(IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
