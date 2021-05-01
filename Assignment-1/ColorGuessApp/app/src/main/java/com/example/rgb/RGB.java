package com.example.rgb;

public class RGB {

    double r, g, b;

    public RGB() {
        this.r = (Math.floor(Math.random() * 256));
        this.g = (Math.floor(Math.random() * 256));
        this.b = (Math.floor(Math.random() * 256));
    }

    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    @Override
    public String toString() {
        return "RGB(" + r + ", " + g + ", " + b + ')';
    }
}