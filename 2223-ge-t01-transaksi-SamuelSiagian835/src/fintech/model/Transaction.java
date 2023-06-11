package fintech.model;

/**
 * @author 12S21010 Bobby Siagian
 * @author 12S21042 Samuel Siagian
 */
public class Transaction { //member

    private int id = 1;
    private String acount;
    private String account2;
    private String name;
    private Double amount;
    private String posted_at;
    private String note;
    private Double balance = 0.0;

    public Transaction(String _name, String _acount) {      //constractor
        this.name = _name;
        this.acount = _acount;
    }

    public Transaction(String _account2, Double _amount, String _posted_at, String _note) {     //constractor
        this.account2 = _account2;

        this.amount = _amount;
        this.posted_at = _posted_at;
        this.note = _note;
    }
        // source action generate getters
    public int getId() {
        return id;
    }

    public String getAcount() {
        return acount;
    }

    public Double getAmount() {
        return amount;
    }

    public String getPosted_at() {
        return posted_at;
    }

    public String getNote() {
        return note;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return this.acount + "|" + this.name + "|" + this.balance;      //metod
    }

    public String toString2() {
        return this.id + "|" + this.account2 + "|" + this.amount + "|" + this.posted_at + "|" + this.note + "|"
                + (this.balance + amount);
    }

    public String getAccount2() {
        return account2;
    }

}