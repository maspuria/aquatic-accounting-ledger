package com.pluralsight;

import java.util.Scanner;

public class Application {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;

        while (running) {
            displayHomeScreenMenu();
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    System.out.println("Add Deposit");
                    break;
                case "P":
                    System.out.println("Make Payment (Deposit)");
                    break;
                case "L":
                    runLedgerMenu();
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
    // I made my Home Screen Menu into a method so that my main method can be clean and organized
    public static void displayHomeScreenMenu() {
        System.out.println("==== Home Screen Menu ====");
        System.out.println("(D) Add Deposit ");
        System.out.println("(P) Make Payment (Deposit) ");
        System.out.println("(L) Ledger Menu");
        System.out.println("(X) Exit ");
        System.out.print("Enter choice: ");
    }

    // Creating the Ledger Menu
    public static void runLedgerMenu() {
        boolean running = true;
        while (running) {
            System.out.println("==== Ledger Menu ====");
            System.out.println("(A) Display All Entries ");
            System.out.println("(D) Display Deposits "); // only entries that deposits into the account
            System.out.println("(P) Display Payments "); // only negative entries (payments)
            System.out.println("(R) Reports "); // another menu screen that allows custom searches
            System.out.println("(H) Home"); // takes you back to the original home screen menu
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    System.out.println("Displaying All Entries");
                    break;
                case"D":
                    System.out.println("Displaying Deposits only");
                    break;
                case"P":
                    System.out.println("Displaying Payments");
                    break;
                case"R":
                    System.out.println("Displaying Reports Menu now");
                    break;
                case"H":
                    running = false; // this will exit the ledger menu and take you back to the home screen menu
                    break;
                default:
                    System.out.println("Invalid entry choice. Enter A, D, P, R, or H. Please try again.");
            }
        }
    }


}
