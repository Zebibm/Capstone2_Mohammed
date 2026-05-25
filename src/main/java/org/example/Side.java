package org.example;

public class Side { private String name;
    private double price;

    // Constructor
    public Side(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Side details
    public String getSideDetails() {

        return name + " - $" +
                String.format("%.2f", price);
    }
}

