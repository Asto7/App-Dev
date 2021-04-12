package com.example.lab_8;

public class ValidateInput {
    String id, name, price, mrp;

    ValidateInput(String id, String name, String price, String mrp) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mrp = mrp;
    }

    public String valid() {
        if (this.name.length() < 3) {
            return "Product Name should be of atleast 3 length";
        }
        if (this.id.length() < 3) {
            return "Product Id should be of atleast 3 length";
        }
        if (this.price.length() <= 0) {

            return "Please Enter Product Price!";
        }
        if (this.mrp.length() <= 0) {
            return "Please Enter Product Mrp!";
        }

        Double pr = Double.parseDouble(this.price);
        Double mrp = Double.parseDouble(this.mrp);

        if (pr < 0) {
            return "Product Sell Price Should be positive!";
        }
        if (mrp < 0) {
            return "Product Mrp Should be positive!";
        }
        return "";
    }
}
