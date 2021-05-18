package nl.inholland.inf2a.groep4.inhollandapiproject.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private int id;
    private LocalDate timestamp;
    private String creator;
    private String from;
    private String to;
    private double amount;

    public Transaction(int id)
    {
        this.id = id;
    }

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {this.timestamp = timestamp;}

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
