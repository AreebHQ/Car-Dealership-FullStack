package com.ds.dealership.entities;

import javax.persistence.*;

@Entity
public class Sales {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sales_id")
    private int id;
    @Column(name = "sale_amount")
    private double saleTotal;
    @Column (name = "vehicle")
    private int vechicleCount;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
