package org.example;

public class Topping {
    private String name;
    private String type;   // REGULAR or PREMIUM
    private double price;

    // Constructor
    public Topping(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    //  display info
    public String getToppingInfo() {
        return name + " (" + type + ") - $" + price;
    }
}

