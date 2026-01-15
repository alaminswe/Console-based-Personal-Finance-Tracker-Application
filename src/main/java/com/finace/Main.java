package com.finace;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Alamin's Finance Application!");

        List<Transaction> transactions = new ArrayList<>();

        while(true){
            System.out.println("\nMenu:");
            System.out.println("1. Create Transaction (Add Income or Expense)");
            System.out.println("2. Read All Transaction (View All)");
            System.out.println("3. Update Transaction");
            System.out.println("4. Delete Transaction");
            System.out.println("5. Calculate Balance");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.println("Creating Transaction...");

                    String id =""+(transactions.size() + 1);

                    System.out.print("Enter type (income/expense): ");
                    String type = sc.nextLine();

                    if(!type.equals("income") && !type.equals("expense")){
                        System.out.println("Invalid type. Transaction creation aborted.");
                        break;
                    }

                    System.out.print("Enter amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine(); // consume newline

                    String description = type.equals("income") ? "Salary" : "Groceries";

                    LocalDate date = takeDateInput();

                    Transaction t = new Transaction(id, type, amount, description, date.toString());

                    transactions.add(t);
                    System.out.println("Transaction added: " + t);
                    break;

                case 2:
                    System.out.println("Listing All Transactions...");
                    if(transactions.isEmpty()){
                        System.out.println("No transactions found.");
                        break;
                    }

                    for(Transaction transaction : transactions){
                        System.out.println(transaction);
                    }
                    break;

                case 3:
                    System.out.println("Update the transection instance that you want to update...");

                    System.out.println("Available Transactions:");
                    if(transactions.isEmpty()){
                        System.out.println("No transactions found.");
                        break;
                    }
                    for(Transaction transaction : transactions){
                        System.out.println(transaction);
                    }
                    System.out.print("Enter Transaction ID to update: ");
                    String updateId = sc.nextLine();
                    Transaction toUpdate = null ;
                    for(Transaction transaction : transactions){
                        if(transaction.getId().equals(updateId)){
                            // Found the transaction to update
                            toUpdate = transaction;
                            break;
                        }
                    }


                    if(toUpdate != null){
                        System.out.println("Which Section do you want to update : ");
                        System.out.println("1. Type");
                        System.out.println("2. Amount");
                        System.out.println("3. Date");
                        System.out.print("Enter choice: ");

                        int updateChoice = sc.nextInt();
                        sc.nextLine(); // consume newline

                        switch(updateChoice){
                            case 1:
                                System.out.print("Enter new type (income/expense): ");
                                String newType = sc.nextLine();
                                if (newType.equals("income") || newType.equals("expense")) {
                                    toUpdate.setType(newType);
                                    System.out.println("Type updated successfully.");
                                } else {
                                    System.out.println("Invalid type. Update aborted.");
                                }
                                break;
                            case 2:
                                System.out.print("Enter new amount: ");
                                double newAmount = sc.nextDouble();
                                sc.nextLine();
                                toUpdate.setAmount(newAmount);
                                System.out.println("Amount updated successfully.");
                                break;
                            case 3:
                                LocalDate newDate = takeDateInput();
                                toUpdate.setDate(newDate.toString());
                                System.out.println("Date updated successfully.");
                                break;
                            default:
                                System.out.println("Invalid choice. Update aborted.");
                                continue;
                        }

                        System.out.println("Transaction updated: " + toUpdate);
                    } else {
                        System.out.println("Transaction ID not found. Update aborted.");
                    }
                    break;

                case 4:
                    System.out.println("Delete the transection instance that you want to delete...");
                    System.out.println("Available Transactions:");
                    if(transactions.isEmpty()) {
                        System.out.println("No transactions found.");
                    }
                    for(Transaction transaction : transactions){
                        System.out.println(transaction);
                    }
                    System.out.print("Enter Transaction ID to delete: ");
                    String deleteId = sc.nextLine();
                    Transaction toDelete = null;
                    for(Transaction transaction : transactions){
                        if(transaction.getId().equals(deleteId)){
                            toDelete = transaction;
                            break;
                        }
                    }

                    if(toDelete != null){
                        transactions.remove(toDelete);
                        System.out.println("Transaction deleted: " + toDelete);
                    } else {
                        System.out.println("Transaction ID not found. Deletion aborted.");
                    }
                    break;

                case 5:
                    System.out.println("Calculating Balance...");
                    double balance = 0.0;
                    for(Transaction transaction : transactions){
                        if(transaction.getType().equals("income")){
                            balance += transaction.getAmount();
                        } else {
                            balance -= transaction.getAmount();
                        }
                    }
                    System.out.println("Current Balance: " + balance);
                    break;

                case 6:
                    System.out.println("Exiting application. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public static LocalDate takeDateInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose date input option:");
        System.out.println("1. Enter date manually");
        System.out.println("2. Use today's date");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        if(choice == 1){
            System.out.print("Enter year (YYYY): ");
            int year = sc.nextInt();

            System.out.print("Enter month (1-12): ");
            int month = sc.nextInt();

            System.out.print("Enter day (1-31): ");
            int day = sc.nextInt();

            return LocalDate.of(year, month, day);
        } else {
            return LocalDate.now();
        }
    }
}