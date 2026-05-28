package org.example;

public class Topping {

    // =========================
    // FIELDS
    // =========================

    private String name;
    private String type;
    private double price;

    // =========================
    // CONSTRUCTOR
    // =========================

    public Topping(String name, String type, double price) {

        this.name = name;
        this.type = type;
        this.price = price;
    }

    // =========================
    // GETTERS
    // =========================

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    // =========================
    // TOPPING DETAILS
    // =========================

    @Override
    public String toString() {

        return name + " ($" + price + ")";
    }
}