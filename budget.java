package com.example.demo;
import java.util.Scanner;


public class budget {
    Scanner scanner = new Scanner(System.in);

    double income;
    double expense;
    double groceries;
    double utilities;
    double entertainment;
    double transportation;
    double rent;
    double savings;


    public void budgetQuestions () {

        System.out.println("What is your income ?");
        income = scanner.nextDouble();

        System.out.println("How much do you spend on groceries ?");
        groceries = scanner.nextDouble();

        System.out.println("How much do you spend on utilities ?");
        utilities = scanner.nextDouble();

        System.out.println("How much do you spend on entertainment ?");
        entertainment = scanner.nextDouble();

        System.out.println("How much do you spend on transportation ?");
        transportation = scanner.nextDouble();

        System.out.println("How much do you spend on rent ?");
        rent = scanner.nextDouble();

        System.out.println("How much do you spend on savings ?");
        savings = scanner.nextDouble();


        double totalExpenses = groceries + utilities +
                entertainment + transportation +
                rent + savings;

        double total = income - totalExpenses;

        if(total < 0) {
            System.out.println("Your over budget by$" + Math.abs(total));
        } else{
            System.out.println("You have a balance of $" + total);
        }

        System.out.println("Expense breakdown");
        System.out.println("Groceries: " + (groceries/totalExpenses) * 100 + "%");
        System.out.println("Utilities: " + (utilities/totalExpenses) * 100 + "%");
        System.out.println("Entertainment: " + (entertainment/totalExpenses) * 100 + "%");
        System.out.println("Transportation: " + (transportation/totalExpenses) * 100 + "%");
        System.out.println("Rent: " + (rent/totalExpenses) * 100 + "%" );
        System.out.println("Savings: " + (savings/totalExpenses) * 100 + "%");

    }

}
