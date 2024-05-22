package dev.neri.site.service;

import dev.neri.site.dao.DropletRepository;
import dev.neri.site.entity.Droplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DropletService {
    private final DropletRepository repository;

    @Autowired
    public DropletService(DropletRepository dropletRepository) {
        repository = dropletRepository;
    }

    public List<Droplet> findAll() {
        return repository.findAll();
    }

    public Droplet saveDroplet(Droplet newDroplet){
        return repository.save(newDroplet);
    }
}
