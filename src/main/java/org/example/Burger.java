package org.example;

import java.util.ArrayList;

public class Burger {

    // =========================
    // FIELDS
    // =========================

    private String size;
    private String bunType;
    private boolean stuffed;

    private ArrayList<Topping> toppings;

    // =========================
    // CONSTRUCTOR
    // =========================

    public Burger(String size, String bunType, boolean stuffed) {

        this.size = size;
        this.bunType = bunType;
        this.stuffed = stuffed;

        toppings = new ArrayList<>();
    }

    // =========================
    // ADD TOPPING
    // =========================

    public void addTopping(Topping topping) {

        toppings.add(topping);
    }

    // =========================
    // GETTERS
    // =========================

    public String getSize() {
        return size;
    }

    public String getBunType() {
        return bunType;
    }

    public boolean isStuffed() {
        return stuffed;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    // =========================
    // CALCULATE PRICE
    // =========================

    public double calculatePrice() {

        double basePrice = 0;

        // Burger size price
        switch (size.toLowerCase()) {

            case "small":
                basePrice = 7.00;
                break;

            case "medium":
                basePrice = 9.00;
                break;

            case "large":
                basePrice = 11.00;
                break;
        }

        // Stuffed burger extra charge
        if (stuffed) {
            basePrice += 2.00;
        }

        // Add toppings price
        for (Topping topping : toppings) {

            basePrice += topping.getPrice();
        }

        return basePrice;
    }

    // =========================
    // BURGER DETAILS
    // =========================

    public String getBurgerDetails() {

        StringBuilder sb = new StringBuilder();

        sb.append("\n")
                .append(size)
                .append(" Burger\n");

        sb.append("Bun: ")
                .append(bunType)
                .append("\n");

        sb.append("Stuffed: ")
                .append(stuffed ? "Yes" : "No")
                .append("\n");

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