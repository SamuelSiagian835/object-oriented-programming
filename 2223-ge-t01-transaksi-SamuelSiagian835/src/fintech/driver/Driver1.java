package fintech.driver;

/**
 * @author 12S21010 Bobby Siagian
 * @author 12S21042 Samuel Siagian
 */

import java.util.Scanner;

import fintech.model.Account;

public class Driver1 {

    public static void main(String[] _args) {
            Scanner sc = new Scanner(System.in);

            String command = sc.nextLine();
            String owner = sc.nextLine();
            String name = sc.nextLine();

            Account account = new Account(owner, name);
            System.out.println(account.toString()); 

            
            sc.close();
        

    }

}