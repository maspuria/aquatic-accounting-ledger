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
                    System.out.println("Displaying All Entries"); //feature: runAllEntries
                    break;
                case "D":
                    System.out.println("Displaying Deposits only"); //feature: runDeposits
                    break;
                case "P":
                    System.out.println("Displaying Payments"); // feature: runPayments
                    break;
                case "R":
                    runReportsMenu();
                    break;
                case "H":
                    running = false; // this will exit the ledger menu and take you back to the home screen menu
                    break;
                default:
                    System.out.println("Invalid entry choice. Enter A, D, P, R, or H. Please try again.");
            }
        }
    }

    // Creating the Reports Menu
    public static void runReportsMenu() {
        boolean running = true;

        while (running) {
            System.out.println(" ==== Reports Menu ==== "); // this menu allows users to run pre-defined reports or run a custom search
            System.out.println("(1) Month to Date ");
            System.out.println("(2) Previous Month ");
            System.out.println("(3) Year to Date ");
            System.out.println("(4) Previous Year ");
            System.out.println("(5) Search by Vendor "); // prompt user for vendor name and display all entries for that vendor
            System.out.println("(0) Back "); // takes user back to Ledger Menu
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim(); // doesn't need toUpperCase() bc user choice will be numerical entries

            switch (choice) {
                case "1":
                    System.out.println("Display month to date entries..."); // feature: runMonthToDate
                    break;
                case"2":
                    System.out.println("Display Previous Month entries..."); // feature: runPreviousMonth
                    break;
                case "3":
                    System.out.println("Display Year to Date entries..."); // ft: runYearToDate
                    break;
                case"4":
                    System.out.println("Display Previous Year entries..."); //ft: runPreviousYear
                    break;
                case"5":
                    System.out.println("Searching by vendor..."); // ft: runSearchByVendor
//                    System.out.println("==== Search by Vendor ====");
//                    System.out.print("Which vendor transactions would you like to display?");
//                    System.out.println("Enter Vendor: ");
                    break;
                case"0":
                    running = false; // takes user back to the Ledger menu
                    break;
                default:
                    System.out.println("Invalid entry choice. Enter 1, 2, 3, 4, 5, or 0. Please try again.");

            }

        }
    }


}
