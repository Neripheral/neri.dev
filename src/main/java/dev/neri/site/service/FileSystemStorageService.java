package dev.neri.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@EnableConfigurationProperties(StorageProperties.class)
public class FileSystemStorageService implements StorageService{
    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties){
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void store(MultipartFile file, int id) {
        try{
            if(file.isEmpty())
                throw new StorageException("File is empty.");

            Path destinationFile =
                    this.rootLocation.resolve(Paths.get(Integer.toString(id)))
                            .normalize()
                            .toAbsolutePath();

            if(!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath()))
                throw new StorageException("Cannot store file outside current directory.");

            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch(IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Resource load(int id) {
        try{
            Path requestedFilePath = this.rootLocation.resolve(String.valueOf(id));
            Resource requestedFile = new UrlResource(requestedFilePath.toUri());
            if(requestedFile.exists())
                return requestedFile;
            else
                throw new StorageException(String.format("Could not read file with id %d", id));
        } catch(MalformedURLException e){
            throw new StorageException(String.format("Could not read file with id %d", id));
        }
    }

    private static class StorageException extends RuntimeException {
        public StorageException(String s) {super(s);}
        public StorageException(String s, Throwable cause) {
            super(s, cause);
        }
    }
}
