package dev.neri.site.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SimpleFileTransferRestControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String urlOf(String path){
        return "http://localhost:" + port + "/simplefile" + path;
    }

    @Test
    void contextLoads(){
        assertThat(port).isNotEqualTo(0);
        assertThat(restTemplate).isNotNull();
    }

    @Test
    void getPingReturnsPong(){
        assertThat(
                restTemplate.getForObject("http://localhost:" + port + "/simplefile/ping", String.class))
                .contains("Pong");
    }

    @Test
    public void getSimpleImageReturnsAnImageMIMEType() {
        ResponseEntity<byte[]> response = restTemplate.getForEntity(urlOf("/image"), byte[].class);
        assertThat(response.getHeaders().getContentType()).isEqualTo("image/jpeg");
    }

    @Test
    public void getSimpleDocumentReturnsAnPdfMIMEType() {
        ResponseEntity<byte[]> response = restTemplate.getForEntity(urlOf("/document"), byte[].class);
        assertThat(response.getHeaders().getContentType()).isEqualTo("application/pdf");
    }

    @Test
    public void getSimpleArchiveReturnsZipMIMEType() {
        ResponseEntity<byte[]> response = restTemplate.getForEntity(urlOf("/archive"), byte[].class);
        assertThat(Objects.requireNonNull(response.getHeaders().getContentType()).toString()).isEqualTo("application/zip");
    }
}