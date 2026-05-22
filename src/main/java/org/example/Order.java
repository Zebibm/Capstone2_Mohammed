package org.example;
import java.util.ArrayList;
public class Order {
    private ArrayList<Burger> burgers;
    private ArrayList<Drink> drinks;
    private ArrayList<Side> sides;

    // Constructor
    public Order() {

        burgers = new ArrayList<>();
        drinks = new ArrayList<>();
        sides = new ArrayList<>();
    }

    // Add Burger
    public void addBurger(Burger burger) {
        burgers.add(burger);
    }

    // Add Drink
    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    // Add Side
    public void addSide(Side side) {
        sides.add(side);
    }

    // Getters
    public ArrayList<Burger> getBurgers() {
        return burgers;
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public ArrayList<Side> getSides() {
        return sides;
    }

    //  Calculate Total Price
    public double calculateTotal() {

        double total = 0;

        // Burger prices
        for (Burger burger : burgers) {
            total += burger.calculatePrice();
        }

        // Drink prices
        for (Drink drink : drinks) {
            total += drink.getPrice();
        }

        // Side prices
        for (Side side : sides) {
            total += side.getPrice();
        }

        return total;
    }

    //  Order Summary
    public String getOrderSummary() {

        StringBuilder sb = new StringBuilder();

        sb.append("===== THE BURGER LAB =====\n");

        // Burgers
        for (Burger burger : burgers) {
            sb.append("\n");
            sb.append(burger.getBurgerDetails());
        }

        // Drinks
        for (Drink drink : drinks) {
            sb.append("\nDrink: ")
                    .append(drink.getFlavor())
                    .append(" ")
                    .append(drink.getSize())
                    .append("\n");
        }

        // Sides
        for (Side side : sides) {
            sb.append("\nSide: ")
                    .append(side.getName())
                    .append("\n");
        }

        sb.append("\nTOTAL: $")
                .append(String.format("%.2f", calculateTotal()));

        return sb.toString();
    }
}
