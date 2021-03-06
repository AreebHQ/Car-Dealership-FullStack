package com.ds.dealership.Entities;

import javax.persistence.*;

@Entity
public class Color {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "color_id")
    private int id;
    @Column
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
