package com.ds.dealership.entities;

import javax.persistence.*;

@Entity
public class Purchase {
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "purchase_id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String email;
    @Column
    private String street;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private String zip;
    @Column
    private int price;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicleId;
    @ManyToOne
    @JoinColumn(name = "purchase_type")
    private PurchaseType purchaseType;


}
