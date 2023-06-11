package fintech.model;

/**
 * @author NIM Nama
 * @author NIM Nama
 */
public class Account {
    private String accountName;
    private String owner;
    private double balance;

    public Account(String accountName, String owner) {
        this.accountName = accountName;
        this.owner = owner;
        this.balance = 0.0;
    }

    public String toString() {
        return owner + "|" + accountName + "|" + balance;
    }

    // getAccountName
    public String getAccountName() {
        return owner;
    }

    public void addSaldo(double amount) {
        this.balance += amount;
    }

    public void updateBalance(double parseDouble) {
    }

    public String getOwner() {
        return null;
    }


   

}
