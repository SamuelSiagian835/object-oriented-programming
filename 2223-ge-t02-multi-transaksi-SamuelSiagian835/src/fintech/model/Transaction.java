package fintech.model;

/**
 * @author 12S21010 Bobby Siagian
 * @author 12S21042 Samuel Siagian
 */
public class Transaction { //member

    private String owner;
    private String name;
    private String searchkey;
    private Double amount;
    private String date;
    private String note;
    private Double balance = 0.0;

    public Transaction(String _name, String _owner) {      //constractor
        this.name = _name;
        this.owner = _owner;
    }

    public Transaction(String _searchkey, Double _amount, String _date, String _note) {     //constractor
        this.searchkey = _searchkey;
        this.date = _date;
        this.note = _note;

    }
        // source action generate getters

    public String getowner() {
        return owner;
    }

    public String getname() {
        return name;
    }

    public Double amount() {
        return amount;
    }

    public String date() {
        return date;
    }

    public String note() {
        return note;
    }

    public String getsearchkey() {
        return searchkey;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String toString() {
        return this.owner + "|" + this.name + "|" + this.balance;      //metod
    }

    public String toString2() {
        return this.searchkey + "|" + this.amount + "|" + this.date + "|" + this.note + "|" + this.balance;
    }

}