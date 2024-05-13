package dev.neri.site.service;

import dev.neri.site.dao.DropletRepository;
import dev.neri.site.entity.Droplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DropletServiceImpl implements DropletService {
    private DropletRepository repository;

    @Autowired
    public DropletServiceImpl(DropletRepository dropletRepository) {
        repository = dropletRepository;
    }

    @Override
    public List<Droplet> findAll() {
        return repository.findAll();
    }

    @Override
    public Droplet saveDroplet(Droplet newDroplet){
        return repository.save(newDroplet);
    }
}
