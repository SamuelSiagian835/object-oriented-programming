package pbo.fintech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 12S21010 Boby Siagian
 * @author 12S21042 Samuel Siagian
 */

 @Entity
 @Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "accountName", nullable = false, length = 225)
    private String accountName;
    @Column(name = "owner", nullable = false, length = 225)
    private String owner;
    @Column(name = "balance", nullable = false, length = 225)
    private double balance;

    public Account(){
        // 
    }

    public Account(String accountName, String owner) {
        this.accountName = accountName;
        this.owner = owner;
        this.balance = 0.0;
    }

    

    public String getAccountName() {
        return accountName;
    }



    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }



    public String getOwner() {
        return owner;
    }



    public void setOwner(String owner) {
        this.owner = owner;
    }



    public double getBalance() {
        return balance;
    }



    public void setBalance(double balance) {
        this.balance = balance;
    }



    @Override
    public String toString() {
        return accountName + "|" + owner + "|" + balance;
    }

    
}
