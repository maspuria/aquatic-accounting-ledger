package com.pluralsight;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hi! Capstone Week! Woohoo!");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("==== Home Screen Menu ====");
            System.out.println("(D) Add Deposit ");
            System.out.println("(P) Make Payment (Deposit) ");
            System.out.println("(L) Ledger Menu");
            System.out.println("(X) Exit ");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    System.out.println("Add Deposit");
                    break;
                case "P":
                    System.out.println("Make Payment (Deposit)");
                    break;
                case "L":
                    System.out.println("Ledger Menu");
                    break;
                case "X":
                    System.out.println("You selected Exit. Thank you for using Aquatic Accounting Ledger... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid entry choice. Enter D, P, L, or X. Please try again. ");
            }

        }
        scanner.close();

    }

}
