package com.ds.dealership.entities;

import javax.persistence.*;

@Entity
public class Vehicle {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "vehicle_id")
    private int id;
    @Column
    private String type;
    @Column
    private String year;
    @Column
    private String transmission;
    @Column
    private int mileage;
    @Column(name = "vin_number")
    private String vinNumber;
    @Column (name = "mrsp_price")
    private int mrspPrice;
    @Column (name = "sale_price")
    private int salePrice;
    @Column
    private String description;
    @Column
    private String image;
    @Column
    private boolean featured;
    @Column
    private boolean sold;

    @ManyToOne
    @JoinColumn(name = "body_color")
    private Color bodyColor;
    @ManyToOne
    @JoinColumn(name = "interior_color")
    private Color interiorColor;
    @ManyToOne
    @JoinColumn(name = "body")
    private Body body;
    @ManyToOne
    @JoinColumn(name = "make")
    private Make make;
    @ManyToOne
    @JoinColumn(name = "model")
    private Model model;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public int getMrspPrice() {
        return mrspPrice;
    }

    public void setMrspPrice(int mrspPrice) {
        this.mrspPrice = mrspPrice;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Color getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(Color bodyColor) {
        this.bodyColor = bodyColor;
    }

    public Color getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(Color interiorColor) {
        this.interiorColor = interiorColor;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
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
