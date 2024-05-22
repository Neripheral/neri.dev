package dev.neri.site.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {
    void store(MultipartFile file, int id);

    Path load(String filename);
}
