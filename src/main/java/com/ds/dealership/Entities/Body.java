package com.ds.dealership.Entities;

import javax.persistence.*;

@Entity
public class Body {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "body_id")
    private int Id;
    @Column
    private String name;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
