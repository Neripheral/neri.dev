package dev.neri.site.controller;

import dev.neri.site.dao.DropletRepository;
import dev.neri.site.entity.Droplet;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
public class DropletTest {
    @Resource
    private DropletRepository dropletRepository;

    @Test
    public void at_start_no_droplets_exist() {
        List<Droplet> existingDroplets = dropletRepository.findAll();
        assertThat(existingDroplets).isEmpty();
    }
}
