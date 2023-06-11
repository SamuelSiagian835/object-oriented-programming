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

public class Driver2 {

    public static void main(String[] _args) {
        ArrayList<Account> accounts = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int i = 0;

        String[] arrsave = new String[50];

        while (true) {
            String command = sc.nextLine();
            if (command.equals("---")) {
                break;
            }

            String[] parts = command.split("#");

            if (parts[0].equals("create-account")) {
                String owner = parts[1];
                String name = parts[2];

                Account account = new Account(owner, name);
                accounts.add(account);

            }
            if (parts[0].equals("find-account")) {
                String searchkey = parts[1];
                for (Account Linaccount : accounts) {
                    if (searchkey.toLowerCase().equals(Linaccount.getName().toLowerCase())) {

                        arrsave[i] = Linaccount.toString();
                    }
                }

                i++;
            }

        }

        for (Account account : accounts) {
            System.out.println(account.toString());
        }

        for (int j = 0; j < i; j++) {
            if (arrsave[j] != null) {
                System.out.println(arrsave[j]);
            }

        }

        sc.close();
    }

}