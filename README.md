# Capstone Project 1 : Accounting Ledger Application
## Aquatic Accounting Ledger
My Capstone 1 Project is a Java based Console Line Interface (CLI) Application
that will allow users to record, view, and manage financial transactions for an
Aquatic Store. It stores all transactions inside a 'transactions.csv' file so 
users can track their transactions whether it is income or expenses in a simple
and organized way. 

### Project Features
#### Home Screen Menu
  - Navigates through all the core functions in simple, organized interface.
  - User options include **Add Deposit**, **Make Payment**, **Ledger Menu**, and **Exit**.

#### Deposits & Payments
- Easily record the **income and expenses** with a few user input functions.
- Payments will be recorded into your transactions.csv file as **negative** amounts to reflect debits.

#### Ledger Menu
- Displays all transactions from your `transactions.csv` file. 
- Organized reports for: 
  - **All Deposits**
  - **Deposits only**
  - **Payments only**
- Transactions display from **newest to oldest** recorded data

#### Reports Menu 
- Generates financial summaries quickly:
  - **Month to Date** transactions
  - **Previous Month** transactions
  - **Year to Date** transactions
  - **Previous Year** transactions
  - **Search by Vendor** allows the user to find all transactions for a specific name or business.

#### File Handling
- All data is saved persistently inside `transactions.csv` using **FileWriter** and **BufferedReader**.

#### Console Design
- Styled menus with borders and symbols for a polished and easy to read CLI.
- Displays messages confirming successful actions to improve the user experience.


  
