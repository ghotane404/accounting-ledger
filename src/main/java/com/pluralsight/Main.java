package com.pluralsight;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    private static Ledger ledger;
    private Reports reports;

    public static void main (String[] args){
        //The home screen should give the user the following options. The
        //application should continue to run until the user chooses to exit.
        ledger = Ledger.createLoadedLedger();
        homeScreen();
    }

    public static void homeScreen(){
        int width = 40;
        while (true){
            System.out.println();
            UserInterface.printCentered("Home Page", width);
            System.out.println("What would you like to do? ");
            System.out.println("D) Add Deposit - prompt user for the deposit information and save it to the csv file");
            System.out.println("P) Make Payment (Debit) - prompt user for the debit information and save it to the csv file");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            System.out.print("Enter your choice: ");
            String userChoice = scanner.nextLine().toUpperCase().strip();

            switch(userChoice){
                case "D":
                    ledger.addDeposit();
                    break;
                case "P":
                    ledger.addPayment();
                    break;
                case "L":
                    ledger.ledgerScreen();
                    break;
                case "X":
                    System.out.println();
                    System.out.println("Thank you for using StrawHat Accounting Ledger. See you next time!");
                    return;
                default:
                    System.out.println();
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
    }
}