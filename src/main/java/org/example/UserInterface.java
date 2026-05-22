package org.example;

import java.util.Scanner;
import com.burgerlab.models.Order;

public class UserInterface {

    private Scanner scanner;
    private boolean running;

    public UserInterface() {
        scanner = new Scanner(System.in);
        running = true;
    }


    public void start() {

        while (running) {
            showHomeScreen();
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    startNewOrder();
                    break;

                case 0:
                    exitApp();
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void showHomeScreen() {
        System.out.println("\n===== THE BURGER LAB =====");
        System.out.println("1) New Order");
        System.out.println("0) Exit");
        System.out.print("Choose option: ");
    }

    private void startNewOrder() {

        Order order = new Order();

        boolean ordering = true;

        while (ordering) {

            System.out.println("\n----- ORDER MENU -----");
            System.out.println("1) Add Burger");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Side");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Burger feature coming next...");
                    break;

                case 2:
                    System.out.println("Drink feature coming next...");
                    break;

                case 3:
                    System.out.println("Side feature coming next...");
                    break;

                case 4:
                    System.out.println("Checkout coming next...");
                    break;

                case 0:
                    System.out.println("Order cancelled.");
                    ordering = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void exitApp() {
        System.out.println("Thank you for using The Burger Lab!");
        running = false;
    }
}
