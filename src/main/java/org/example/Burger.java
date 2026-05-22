package org.example;

import java.util.ArrayList;

public class Burger {

    private String size;          // 8, 12, 16
    private String bunType;       // white, wheat, brioche
    private boolean stuffed;      // stuffed crust option
    private ArrayList<Topping> toppings;

    // Constructor
    public Burger(String size, String bunType, boolean stuffed) {
        this.size = size;
        this.bunType = bunType;
        this.stuffed = stuffed;
        this.toppings = new ArrayList<>();
    }

    // Add topping
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    // Getters and Setters
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBunType() {
        return bunType;
    }

    public void setBunType(String bunType) {
        this.bunType = bunType;
    }

    public boolean isStuffed() {
        return stuffed;
    }

    public void setStuffed(boolean stuffed) {
        this.stuffed = stuffed;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    // PRICE CALCULATION
    public double calculatePrice() {

        double price = 0;

        // Base price by size
        if (size.equals("8")) {
            price += 8.50;
        } else if (size.equals("12")) {
            price += 12.00;
        } else if (size.equals("16")) {
            price += 16.50;
        }

        // Stuffed crust extra cost
        if (stuffed) {
            price += 2.00;
        }

        // Toppings price
        for (Topping topping : toppings) {
            price += topping.getPrice();
        }

        return price;
    }

    //  RECEIPT DISPLAY
    public String getBurgerDetails() {

        StringBuilder sb = new StringBuilder();

        sb.append(size).append("\" Burger\n");
        sb.append("Bun: ").append(bunType).append("\n");
        sb.append("Stuffed: ").append(stuffed).append("\n");
        sb.append("Toppings:\n");

        if (toppings.isEmpty()) {
            sb.append("- No toppings\n");
        } else {
            for (Topping topping : toppings) {
                sb.append("- ")
                        .append(topping.getName())
                        .append(" ($")
                        .append(topping.getPrice())
                        .append(")\n");
            }
        }

        sb.append("Burger Price: $")
                .append(String.format("%.2f", calculatePrice()))
                .append("\n");

        return sb.toString();
    }
}