package nl.inholland.inf2a.groep4.inhollandapiproject.model;
import nl.inholland.inf2a.groep4.inhollandapiproject.enums.AccountType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_table")
public class Account {
    @Id
    @GeneratedValue
    private int id;
    private int owner;
    private String iban;
    private AccountType type;
    private double balance;
    private double limit;

    public Account(int id, int owner, String iban, AccountType type, double balance, double limit) {
        this.id = id;
        this.owner = owner;
        this.iban = iban;
        this.type = type;
        this.balance = balance;
        this.limit = limit;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}
