package org.example;

public class Drink {

    // =========================
    // FIELDS
    // =========================

    private String size;
    private String flavor;
    private double price;

    // =========================
    // CONSTRUCTOR
    // =========================

    public Drink(String size, String flavor, double price) {

        this.size = size;
        this.flavor = flavor;
        this.price = price;
    }

    // =========================
    // GETTERS
    // =========================

    public String getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    public double getPrice() {
        return price;
    }

    // =========================
    // DRINK DETAILS
    // =========================

    @Override
    public String toString() {

        return flavor + " " + size + " ($" + price + ")";
    }
}


