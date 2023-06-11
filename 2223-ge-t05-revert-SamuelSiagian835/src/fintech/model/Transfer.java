package fintech.model;

public class Transfer extends Transaction{

    private String senderName;
    private String recipientName;

    public Transfer(String senderName, String recipientName, double amount, String postedAt,
                String note) {
        super("", note, amount, postedAt, note);
        this.senderName = senderName;
        this.recipientName = recipientName;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getRecipientName() {
        return recipientName;
    }
    
    public String toStringTran() {
        return this.getId() + "|" + this.getAccountName() + "|" + this.getreceinama() + "|"
                + this.getAmount() + "|" + this.getWaktuTransaksi() + "|" + this.getNote();
    }

    @Override
    public boolean isValid() {
        return this.getAmount() > 0 && !this.getSenderName().isEmpty()
                && !this.getRecipientName().isEmpty();
    }

}
