package fintech.driver;

/**
 * 
 * 
 * @author 12S21010 Bobby Siagian
 * @author 12S21042 Samuel Siagian
 */

import java.util.Scanner;

import fintech.model.Account;

import java.util.ArrayList;

public class Driver1 {

    public static void main(String[] _args) {
        ArrayList<Account> accounts = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            if (command.equals("---")) {
                break;
            }

            String[] parts = command.split("#");
            if (parts.length != 3 || !parts[0].equals("create-account")) {
                System.out.println("Invalid command: " + command);
                continue;
            }

            if (parts[0].equals("create-account")) {
                String owner = parts[1];
                String name = parts[2];

                Account account = new Account(owner, name);
                accounts.add(account);
            }
        }
        for (Account account : accounts) {
            System.out.println(account.toString());
        }

        sc.close();
    }

}