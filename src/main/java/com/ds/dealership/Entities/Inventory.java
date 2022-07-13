package com.ds.dealership.Entities;

import javax.persistence.*;

@Entity
public class Inventory {
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "inventory_id")
    private int id;
    @Column
    private String year;
    @Column
    private int count;
    @Column (name = "stock_value")
    private double stockValue;
    @Column
    private String type;
    @ManyToOne
    @JoinColumn(name = "make_id")
    private Make make;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getStockValue() {
        return stockValue;
    }

    public void setStockValue(double stockValue) {
        this.stockValue = stockValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
