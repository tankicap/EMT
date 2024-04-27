package com.example.lab1b.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Housing {
    public Housing() {
    }

    @Id
    @GeneratedValue
    private Long Id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Host host;
    private Integer numRooms;

    private Boolean isRent;

    public Housing(String name, Category category, Host host, Integer numRooms,Boolean isRent) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isRent=isRent;
    }
}
