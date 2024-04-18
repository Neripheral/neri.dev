package dev.neri.site.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SimpleFileTransferRestControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

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
}