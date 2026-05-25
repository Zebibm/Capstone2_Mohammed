package org.example;

public class Drink {private String size;
    private String flavor;
    private double price;

    // Constructor
    public Drink(String size, String flavor, double price) {
        this.size = size;
        this.flavor = flavor;
        this.price = price;
    }

    // Getters
    public String getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    public double getPrice() {
        return price;
    }

    // Drink details
    public String getDrinkDetails() {

        return size + " " + flavor + " - $" +
                String.format("%.2f", price);
    }

}
