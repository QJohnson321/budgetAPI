package org.example.budget_tracker;
import java.util.Scanner;
public class login {
    String theUserName;
    String thePassword;
    Scanner scanner = new Scanner(System.in);
    budget bud = new budget();



    public void userLogin() {
        theUserName = "smooth";
        thePassword = "1234";

        while(true) {
            System.out.println("Enter your username: ");
            String userName = scanner.nextLine();
            System.out.println("Enter your password: ");
            String password = scanner.nextLine();


            if (userName.equals(theUserName) && password.equals(thePassword)) {
                System.out.println("You are logged in!");
                //theMenu();
                bud.budgetQuestions();
                break;
            } else {
                System.out.println("Wrong username or password!");
            }
        }
    }

    public void theMenu() {
        System.out.println("Where is YOUR MONEY GOING");

        System.out.println("How much money do you want to save");
        int savingMoney = scanner.nextInt();


    }
}
