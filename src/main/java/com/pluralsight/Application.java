package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Application {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;

        while (running) {
            displayHomeScreenMenu(); // calls method that displays home screen menu
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) { // used switch statement rather than if-else statements bc it's more organized for multiple options
                case "D": // if the user types D
                    runAddDeposit(); // calls the method that handles adding a deposit
                    break;
                case "P":
                    runMakePayment();
                    break;
                case "L":
                    displayLedgerMenu();
                    break;
                case "X":
                    System.out.println("\n ╔═════════════════════════════════════════════════════════════════════╗ ");
                    System.out.println(" ║          Thank you for using Aquatic Accounting! Goodbye!");
                    System.out.println(" ╚═════════════════════════════════════════════════════════════════════╝");
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
        System.out.println("\n ╔═══════ Home Screen Menu ═════╗");
        System.out.println(" ║ (D) Add Deposit ");
        System.out.println(" ║ (P) Make Payment (Deposit) ");
        System.out.println(" ║ (L) Ledger Menu");
        System.out.println(" ║ (X) Exit ");
        System.out.println(" ╚══════════════════════════════╝");
        System.out.print(" Enter choice: ");

    }

    //Home Screen Menu option (D) Add Deposit Method (money that gets put in)
    public static void runAddDeposit() {
        try {
            System.out.println("\n ════════════════ Add Deposit ═══════════════");
            System.out.print(" Enter description: "); //asks user for description
            String description = scanner.nextLine();

            System.out.print(" Enter vendor: ");
            String vendor = scanner.nextLine();

            System.out.print(" Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // important bc without it, the next nextLine() would read an empty string or skip

            // calls the helper method I created to get the current date and time as an array of two strings
            String[] dateAndTime = getCurrentDateTime();
            String date = dateAndTime[0]; // first element is the date string
            String time = dateAndTime[1];

            // creates new transaction object & calls transaction constructor
            Transaction deposit = new Transaction(date, time, description, vendor, amount);
            recordNewTransaction(deposit); // saves new transaction deposit to csv file

            System.out.println("═══════════════════════════════════════════════════════");
            System.out.println("  Thank you for choosing Aquatic Accounting Ledger!");
            System.out.println("  Your Deposit was successfully saved!");
            System.out.println("═══════════════════════════════════════════════════════");
        } catch (Exception exception) {
            System.out.println("Please try again. There was an error adding your deposit.");
            //exception.printStackTrace();
        }
    }

    //Home Screen Menu option (P) Make Payment (Deposit) Method (money going out)
    public static void runMakePayment() {
        try {
            System.out.println("\n════════════════ Make Payment (Deposit) ════════════════");

            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter vendor: ");
            String vendor = scanner.nextLine();

            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            String[] dateAndTime = getCurrentDateTime();
            String date = dateAndTime[0];
            String time = dateAndTime[1];

            amount = amount * -1; // indicates money is leaving the account (a payment)

            Transaction deposit = new Transaction(date, time, description, vendor, amount);
            recordNewTransaction(deposit);

            System.out.println("═══════════════════════════════════════════════════════");
            System.out.println("  Thank you for choosing Aquatic Accounting Ledger!");
            System.out.println("  Your Payment was successfully saved!");
            System.out.println("═══════════════════════════════════════════════════════");
        } catch (Exception exception) {
            System.out.println("Please try again. There was an error adding your payment.");
            //exception.printStackTrace();
        }
    }

    // method for saving a new transaction to the csv file
    public static void recordNewTransaction(Transaction transaction) { // taking transaction object as parameter
        try {
            // create a FileWriter object so user can write to the file
            FileWriter writer = new FileWriter("transactions.csv", true);
            writer.write(transaction.getDate() + "|" + //transaction.getDate() calls getDate() method on transaction object
                    transaction.getTime() + "|" +
                    transaction.getDescription() + "|" +
                    transaction.getVendor() + "|" +
                    transaction.getAmount() + "\n");
            writer.close();
        } catch (IOException exception) { // catch just in case there is input/output error
            System.out.println("Error: an unexpected error occurred ");
        }

    }

    // method for new transactions for the current date and time
    public static String[] getCurrentDateTime() {
        LocalDate date = LocalDate.now(); // gets today's date
        LocalTime time = LocalTime.now();

        //formatters specify the date format/time format
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm:ss");

        String dateFormatted = date.format(dateFmt); // converts LocalDate to String
        String timeFormatted = time.format(timeFmt);

        return new String[]{dateFormatted, timeFormatted}; //(returns a string array [date,time])
    }

    // Creating the Ledger Menu
    public static void displayLedgerMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n ╔════════ Ledger Menu ════════╗");
            System.out.println(" ║ (A) Display All Entries ");
            System.out.println(" ║ (D) Display Deposits "); // only positive entries that deposits into the account
            System.out.println(" ║ (P) Display Payments "); // only negative entries
            System.out.println(" ║ (R) Reports Menu"); // another menu screen that allows custom searches
            System.out.println(" ║ (H) Home");
            System.out.println(" ╚═════════════════════════════╝");
            System.out.print(" Enter choice: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    runAllEntries(); // calls this method to show all entry transactions
                    break;
                case "D":
                    runDeposits(); //only shows deposit transactions
                    break;
                case "P":
                    runPayments();
                    break;
                case "R":
                    displayReportsMenu();
                    break;
                case "H":
                    running = false; // this will exit the ledger menu and take you back to the home screen menu
                    break;
                default:
                    System.out.println("Invalid entry choice. Enter A, D, P, R, or H. Please try again.");
            }
        }
    }

    // method to read all transactions from csv file & returns them as an ArrayList
    public static ArrayList<Transaction> readTransactions() {

        ArrayList<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            reader.readLine(); // important to skip the header of csv file
            while ((line = reader.readLine()) != null) { //keeps reading until readLine() returns null
                String[] parts = line.split("\\|");
                if (parts.length == 5) { // use if statement to make sure there is 5 parts
                    String date = parts[0].trim(); // index 0 = date
                    String time = parts[1].trim();
                    String description = parts[2].trim();
                    String vendor = parts[3].trim();
                    double amount = Double.parseDouble(parts[4].trim()); // parse amount string to double
                    // creates a new Transaction object with this data and adds it to ArrayList
                    transactions.add(new Transaction(date, time, description, vendor, amount));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions.csv file: " + e.getMessage());
        }
        Collections.reverse(transactions); //newest transactions show first
        return transactions;
    }

    // Ledger Menu 1st Option (A) Displaying All Entries
    public static void runAllEntries() {
        // calls readTransactions() to return an ArrayList of all transaction objects from csv
        ArrayList<Transaction> transactions = readTransactions();

        System.out.println("\n ╔════════════════════ Aquatic Accounting Ledger ══════════════════════╗ ");
        System.out.println(" ║                         ALL TRANSACTIONS ");
        System.out.println(" ╚═════════════════════════════════════════════════════════════════════╝");

        int count = 1; // number each transaction
        for (Transaction t : transactions) {
            System.out.println(count + ": " + t);
            count++;
        }
    }

    // Ledger Menu Option (D) Display Deposits only (positive amounts)
    public static void runDeposits() {
        // reads all transactions from the transactions.csv file
        ArrayList<Transaction> transactions = readTransactions();

        System.out.println("\n ╔════════════════════ Aquatic Accounting Ledger ══════════════════════╗ ");
        System.out.println(" ║                         ALL DEPOSITS ONLY ");
        System.out.println(" ╚═════════════════════════════════════════════════════════════════════╝");
        int count = 1;
        for (Transaction t : transactions) {
            if (t.getAmount() > 0) {
                System.out.println(count + ": " + t);
                count = count + 1;
            }
        }
    }

    // Ledger Menu Option (P) Displaying Payments only (negative amounts)
    public static void runPayments() {
        ArrayList<Transaction> transactions = readTransactions();

        System.out.println("\n ╔════════════════════ Aquatic Accounting Ledger ══════════════════════╗ ");
        System.out.println(" ║                         ALL PAYMENTS ONLY ");
        System.out.println(" ╚═════════════════════════════════════════════════════════════════════╝");

        int count = 1;
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                System.out.println(count + ": " + t);
                count = count + 1;
            }
        }
    }

    // The Reports Menu
    public static void displayReportsMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n ╔════════ Reports Menu ════════╗");
            System.out.println(" ║ (1) Month to Date ");
            System.out.println(" ║ (2) Previous Month ");
            System.out.println(" ║ (3) Year to Date ");
            System.out.println(" ║ (4) Previous Year ");
            System.out.println(" ║ (5) Search by Vendor ");
            System.out.println(" ║ (0) Back ");
            System.out.println(" ╚══════════════════════════════╝");
            System.out.print(" Enter choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    runMonthToDate();
                    break;
                case "2":
                    runPreviousMonth();
                    break;
                case "3":
                    runYearToDate();
                    break;
                case "4":
                    runPreviousYear();
                    break;
                case "5":
                    runSearchByVendor();
                    break;
                case "0":
                    running = false; // takes user back to the Ledger menu
                    break;
                default:
                    System.out.println("Invalid entry choice. Enter 1, 2, 3, 4, 5, or 0. Please try again.");
            }
        }
    }

    // Reports Menu option (1) Month to Date
    public static void runMonthToDate() { // displays all transactions from current month
        ArrayList<Transaction> transactions = readTransactions();
        ArrayList<Transaction> monthToDate = new ArrayList<>(); // creates list for previous month's transactions
        LocalDate currentDate = LocalDate.now(); // gets today's date
        for (Transaction t : transactions) {
            LocalDate entryDate = LocalDate.parse(t.getDate());

            if (entryDate.getMonthValue() == currentDate.getMonthValue() &&
                    entryDate.getYear() == currentDate.getYear()) {
                monthToDate.add(t);
            }
        }
        System.out.println("\n ╔════════════════════ Aquatic Accounting Ledger ══════════════════════╗ ");
        System.out.println(" ║                           MONTH TO DATE ");
        System.out.println(" ╚═════════════════════════════════════════════════════════════════════╝");

        if (monthToDate.isEmpty()) {
            System.out.println("There are no transactions for this month yet.");
        } else {
            int count = 1;
            for (Transaction t : monthToDate) {
                System.out.println(count + ":" + t);
                count++;
            }
        }
    }

    // Run Reports Menu Option (2) Previous Month
    public static void runPreviousMonth() {
        ArrayList<Transaction> transactions = readTransactions();
        ArrayList<Transaction> previousMonth = new ArrayList<>();

        LocalDate currentDate = LocalDate.now(); // today's date
        LocalDate prevMonthDate = currentDate.minusMonths(1); // 'previous' is holding a month prior to current month
        int lastMonth = prevMonthDate.getMonthValue(); // holds the month number
        int lastMonthsYear = prevMonthDate.getYear(); // holds the last months year

        for (Transaction t : transactions) {
            LocalDate entryDate = LocalDate.parse(t.getDate().trim()); //parse transaction date

            if (entryDate.getMonthValue() == lastMonth &&
                    entryDate.getYear() == lastMonthsYear) {
                previousMonth.add(t);
            }
        }
        System.out.println("\n ╔════════════════════ Aquatic Accounting Ledger ══════════════════════╗ ");
        System.out.println(" ║                           PREVIOUS MONTH ");
        System.out.println(" ╚═════════════════════════════════════════════════════════════════════╝");
        if (previousMonth.isEmpty()) {
            System.out.println("There are no previous month transactions.");
        } else {
            int count = 1;
            for (Transaction t : previousMonth) {
                System.out.println(count + ": " + t);
                count++;
            }
        }
    }

    // Reports Menu Option (3) Year to Date Transactions
    public static void runYearToDate() {
        ArrayList<Transaction> transactions = readTransactions();
        ArrayList<Transaction> yearToDate = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        System.out.println("\n ╔════════════════════ Aquatic Accounting Ledger ══════════════════════╗ ");
        System.out.println(" ║                           YEAR TO DATE ");
        System.out.println(" ╚═════════════════════════════════════════════════════════════════════╝");

        for (Transaction t : transactions) {
            LocalDate entryDate = LocalDate.parse(t.getDate());
            if (entryDate.getYear() == currentYear) {
                yearToDate.add(t);
            }
        }
        if (yearToDate.isEmpty()) {
            System.out.println("There are no transactions for this year yet.");
        } else {
            int count = 1;
            for (Transaction t : yearToDate) {
                System.out.println(count + ": " + t);
                count++;
            }
        }
    }

    // Reports Menu Option (4) Previous Year transactions
    public static void runPreviousYear() {
        ArrayList<Transaction> transactions = readTransactions();
        ArrayList<Transaction> previousYearTransactions = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        int previousYear = currentYear - 1;
        System.out.println("\n ╔════════════════════ Aquatic Accounting Ledger ══════════════════════╗ ");
        System.out.println(" ║                           PREVIOUS YEAR ");
        System.out.println(" ╚═════════════════════════════════════════════════════════════════════╝");
        for (Transaction t : transactions) {
            LocalDate entryDate = LocalDate.parse(t.getDate());
            if (entryDate.getYear() == previousYear) {
                previousYearTransactions.add(t);
            }
        }
        if (previousYearTransactions.isEmpty()) {
            System.out.println("There are no transactions from last year.");
        } else {
            int count = 1;
            for (Transaction t : previousYearTransactions) {
                System.out.println(count + ": " + t);
                count++;
            }
        }
    }

    // Reports Menu Option (5) Search by Vendor
    public static void runSearchByVendor() {
        ArrayList<Transaction> transactions = readTransactions();
        ArrayList<Transaction> vendorMatches = new ArrayList<>();

        System.out.println("\n ╔═══════════════════ Search by Vendor ═══════════════════╗");
        System.out.println(" ║ Which vendor transactions would you like to display?");
        System.out.println(" ╚════════════════════════════════════════════════════════╝");
        System.out.print(" Enter Vendor: ");
        String searchByVendor = scanner.nextLine().trim().toUpperCase();

        for (Transaction t : transactions) {
            String vendorEntry = t.getVendor().toUpperCase();
            if (vendorEntry.contains(searchByVendor)) {
                vendorMatches.add(t);
            }
        }
        if (vendorMatches.isEmpty()) {
            System.out.println("There are no transactions for the vendor you entered.");
        } else {
            System.out.println("\n ╔════════════════════ Aquatic Accounting Ledger ══════════════════════╗ ");
            System.out.println(" ║                      Transactions for " + searchByVendor);
            System.out.println(" ╚═════════════════════════════════════════════════════════════════════╝");
            int count = 1;
            for (Transaction t : vendorMatches) {
                System.out.println(count + ": " + t);
                count++;
            }
        }
    }
}