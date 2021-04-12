package com.example.lab_8;

public class Product {
    private String id;
    private String name;
    private String mrp;
    private String price;

//    public Product(int id, String name, double mrp, double price) {
//        this.id = id;
//        this.name = name;
//        this.mrp = mrp;
//        this.price = price;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}