package dev.neri.site.dao;

import dev.neri.site.entity.Droplet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DropletRepository extends JpaRepository<Droplet, Integer> {}
