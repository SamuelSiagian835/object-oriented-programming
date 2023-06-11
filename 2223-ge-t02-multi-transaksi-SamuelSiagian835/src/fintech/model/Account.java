package fintech.model;

/**
 * @author 12S21010 Bobby Siagian
 * @author 12S21042 Samuel Siagian
 */
public class Account {

    private final String owner;
    private final String name;
    private Double balance = 0.0;

    public Account(String _owner, String _name){
        this.owner = _owner;
        this.name = _name;
    }

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    
    @Override
    public String toString() {
        return this.name + "|" + this.owner + "|" + this.balance;
    }

    public void addTransaction(Transaction transaction) {
    }

    public void add(Transaction transaction) {
    }
}