package com.pluralsight;
import java.util.*;

public class Reports {
    static Scanner scanner = new Scanner(System.in);
    private Reports reports = new Reports();
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public void reportScreen(){
//        R) Reports - A new screen that allows the user to run pre-defined reports or to run a custom search
        System.out.println();
        System.out.println("Please select how you would like to filter the reports: ");
        System.out.println("1) Month To Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search by Vendor - prompt the user for the vendor name and display all entries for that vendor ");

        System.out.println("0) Back - go back to the Ledger page");

        String userChoice = scanner.nextLine().toUpperCase().strip();

        switch (userChoice) {
            case "1":
                monthToDate();
                break;
            case "2":
                previousMonth();
                break;
            case "3":
                yearToDate();
                break;
            case "4":
                previousYear();
                break;
            case "5":
                searchByVendor();
                break;
            case "0":
                System.out.println();
                System.out.println("Going back to ledger page");
                break;
            default:
                System.out.println();
                System.out.println("Invalid selection. Please try again.");
                break;
        }

    }

    private static void monthToDate(){


    }

    private static void previousMonth(){


    }

    private static void yearToDate(){


    }

    private static void previousYear(){


    }

    private static void searchByVendor(){
        System.out.println("5) Search by Vendor - prompt the user for the vendor name and display all entries for that vendor ");


    }


    public static Reports createLoadedReports() {
        Reports reports = new Reports();
//        reports.loadTransaction();
//        reports.displayAllTransactions();

        return reports;
    }



}
