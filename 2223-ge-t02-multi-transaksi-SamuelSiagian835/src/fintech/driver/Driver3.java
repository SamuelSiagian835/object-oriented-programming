package fintech.driver;

/**
 * @author NIM Nama
 * @author NIM Nama
 */
        import java.util.ArrayList;
        import java.util.Scanner;
        import fintech.model.Account;
        import fintech.model.Transaction;
        
        public class Driver3 {
        
            public static void main(String[] _args) {
                String[] arrsave = new String[50];
                ArrayList<Account> accounts = new ArrayList<>();
                Scanner sc = new Scanner(System.in);
                int i = 0;
        
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
        
                    } if (parts[0].equals("find-account")) {
                        String searchkey = parts[1];
                        for (Account Linaccount : accounts) {
                            if (searchkey.toLowerCase().equals(Linaccount.getName().toLowerCase())) {
        
                                arrsave[i] = Linaccount.toString();
                            }
                        }
        
                        i++;
        
                    } else if (parts[0].equals("create-transaction")) {
                        String accountName = parts[1];
                        double amount = Double.parseDouble(parts[2]);
                        String date = parts[3];
                        String note = "";
                        if (parts.length == 5) {
                            note = parts[4];
                        }
        
                        // Cari akun yang sesuai
                        Account account = null;
                        for (Account acc : accounts) {
                            if (acc.getName().equalsIgnoreCase(accountName)) {
                                account = acc;
                                break;
                            }
                        }
        
                        if (account == null) {
                            System.out.println("Account not found, transaction canceled.");
                            continue;
                        }
        
                        // Tambahkan transaksi ke akun
                        Transaction transaction = new Transaction(note, amount, date, note);
                        extracted(account, transaction);
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
                
            }

            private static void extracted(Account account, Transaction transaction) {
                account.add(transaction);
            }
        }
        

    