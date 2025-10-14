package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
                    addDeposit();
                    break;
                case "P":
                    makePayment();
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
        System.out.println("\n ==== Home Screen Menu ====");
        System.out.println("(D) Add Deposit ");
        System.out.println("(P) Make Payment (Deposit) ");
        System.out.println("(L) Ledger Menu");
        System.out.println("(X) Exit ");
        System.out.print("Enter choice: ");
    }

    //Home Screen Menu option (D) Add Deposit Method
    public static void addDeposit() {
        try {
            System.out.println("\n ==== Add Deposit ====");
            String[] dateAndTime = getCurrentDateTime();
            String date = dateAndTime[0];
            String time = dateAndTime[1];

            System.out.print("Enter description: "); //ask user for description
            String description = scanner.nextLine();

            System.out.print("Enter vendor: "); // ask user for vendor
            String vendor = scanner.nextLine();

            System.out.print("Enter amount: ");//ask user for amount
            double amount = scanner.nextDouble();
            scanner.nextLine();

            Transaction deposit = new Transaction(date,time,description,vendor,amount);
            recordNewTransaction(deposit);

            System.out.println("Thank you for choosing Aquatic Accounting Ledger!");
            System.out.println("Your Deposit was successfully saved!");
        }catch (Exception exception){
            System.out.println("Please try again. Error adding your deposit.");
            exception.printStackTrace();
        }
    }

    //Home Screen Menu option (P) Make Payment (Deposit) Method
    public static void makePayment() {
        try {
            System.out.println("\n==== Make Payment (Deposit) ====");
            String[] dateAndTime = getCurrentDateTime();
            String date = dateAndTime[0];
            String time = dateAndTime[1];

            System.out.print("Enter description: "); //ask user for description
            String description = scanner.nextLine();

            System.out.print("Enter vendor: "); // ask user for vendor
            String vendor = scanner.nextLine();

            System.out.print("Enter amount: ");//ask user for amount
            double amount = scanner.nextDouble();
            scanner.nextLine();

            amount = amount * -1;

            Transaction deposit = new Transaction(date,time,description,vendor,amount);
            recordNewTransaction(deposit);

            System.out.println("Thank you for choosing Aquatic Accounting Ledger!");
            System.out.println("Your payment was successfully saved!");
        }catch (Exception exception){
            System.out.println("Please try again. Error adding payment.");
            exception.printStackTrace();
        }
    }
    // method for saving a new transaction
    public static void recordNewTransaction(Transaction transaction) {
        try{
            FileWriter writer = new FileWriter("transactions.csv", true);
            writer.write(transaction.getDate()+ "|" +
                    transaction.getTime() + "|" +
                    transaction.getDescription() +"|" +
                    transaction.getVendor() + "|" +
                    transaction.getAmount() + "\n");
            writer.close();
        } catch (IOException exception) {
            System.out.println("Error: an unexpected error occurred " );
        }

    }
    // method for new transactions for date and time
    private static String[] getCurrentDateTime(){
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm:ss");

        String dateFormatted = date.format(dateFmt);
        String timeFormatted = time.format(timeFmt);

        return new String[]{dateFormatted, timeFormatted};
    }
    // Creating the Ledger Menu
    public static void runLedgerMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n ==== Ledger Menu ====");
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
            System.out.println("\n ==== Reports Menu ==== "); // this menu allows users to run pre-defined reports or run a custom search
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
                case "2":
                    System.out.println("Display Previous Month entries..."); // feature: runPreviousMonth
                    break;
                case "3":
                    System.out.println("Display Year to Date entries..."); // ft: runYearToDate
                    break;
                case "4":
                    System.out.println("Display Previous Year entries..."); //ft: runPreviousYear
                    break;
                case "5":
                    System.out.println("Searching by vendor..."); // ft: runSearchByVendor
//                    System.out.println("==== Search by Vendor ====");
//                    System.out.print("Which vendor transactions would you like to display?");
//                    System.out.println("Enter Vendor: ");
                    break;
                case "0":
                    running = false; // takes user back to the Ledger menu
                    break;
                default:
                    System.out.println("Invalid entry choice. Enter 1, 2, 3, 4, 5, or 0. Please try again.");
            }
        }
    }

}
