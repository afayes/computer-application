package com.ddoscollector.computer.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Computer {

    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String IP;

    @NotBlank
    private String mac;

    @NotNull
    private Date dateAdded;

    private String location;

    public Computer() {
    }

    public Computer(final String id, @NotBlank final String name, @NotBlank final String IP, @NotBlank final String mac,
                    @NotNull final Date dateAdded, final String location) {
        this.id = id;
        this.name = name;
        this.IP = IP;
        this.mac = mac;
        this.dateAdded = dateAdded;
        this.location = location;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Computer computer = (Computer) o;
        return Objects.equals(id, computer.id) &&
                Objects.equals(name, computer.name) &&
                Objects.equals(IP, computer.IP) &&
                Objects.equals(mac, computer.mac) &&
                Objects.equals(dateAdded, computer.dateAdded) &&
                Objects.equals(location, computer.location);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, IP, mac, dateAdded, location);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", IP='" + IP + '\'' +
                ", mac='" + mac + '\'' +
                ", dateAdded=" + dateAdded +
                ", location='" + location + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(final String IP) {
        this.IP = IP;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(final String mac) {
        this.mac = mac;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(final Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }
}
