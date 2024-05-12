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

    @Column(name="coordinate")
    private String coordinate;

    public Droplet() {
    }

    public Droplet(String filename, String coordinate) {
        this.filename = filename;
        this.coordinate = coordinate;
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

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "Droplet{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", coordinate='" + coordinate + '\'' +
                '}';
    }
}
