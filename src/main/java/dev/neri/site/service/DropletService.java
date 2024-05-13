package dev.neri.site.service;

import dev.neri.site.entity.Droplet;

import java.util.List;

public interface DropletService {
    List<Droplet> findAll();

    Droplet saveDroplet(Droplet newDroplet);
}
