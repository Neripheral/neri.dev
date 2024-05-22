package dev.neri.site.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "droplet")
public class Droplet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="filename")
    private String filename;

    public Droplet() {
    }

    public Droplet(String filename) {
        this.filename = filename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "Droplet{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                '}';
    }
}
