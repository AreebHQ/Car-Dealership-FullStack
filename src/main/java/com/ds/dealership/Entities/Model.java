package com.ds.dealership.Entities;

import javax.persistence.*;

@Entity
public class Model {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "model_id")
    private int id;
    @Column
    private String name;
    @Column
    private String date;
    @Column(name = "user_email")
    private String userEmail;

    @ManyToOne
    @JoinColumn(name = "make_id")
    private Make make;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }
}
