package nl.inholland.inf2a.groep4.inhollandapiproject.model;

import nl.inholland.inf2a.groep4.inhollandapiproject.enums.UserType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private UserType type;
    private double minimumBalance;
    private double dailyTotalLimit;
    private double transactionLimit;

    public User(int id, String firstName, String lastName, UserType type, double minimumBalance, double dailyTotalLimit, double transactionLimit) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.minimumBalance = minimumBalance;
        this.dailyTotalLimit = dailyTotalLimit;
        this.transactionLimit = transactionLimit;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public double getDailyTotalLimit() {
        return dailyTotalLimit;
    }

    public void setDailyTotalLimit(double dailyTotalLimit) {
        this.dailyTotalLimit = dailyTotalLimit;
    }

    public double getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(double transactionLimit) {
        this.transactionLimit = transactionLimit;
    }
}
