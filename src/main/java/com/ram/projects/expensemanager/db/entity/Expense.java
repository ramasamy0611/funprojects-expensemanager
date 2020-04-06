package com.ram.projects.expensemanager.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue
    private Long Id;
    @Column("transaction_date")
    private Timestamp transactionDate;
    @Column("opening_balance")
    private Double openingBalance;
    @Column("name")
    private String name;
    @Column("exepense_category")
    private String expenseCategory;
    @Column("transactionType")
    private String transactionType;
    @Column("amount")
    private Double amount;
    @Column("closing_balance")
    private Double closingBalance;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(Double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(Double closingBalance) {
        this.closingBalance = closingBalance;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "Id=" + Id +
                ", transactionDate=" + transactionDate +
                ", openingBalance=" + openingBalance +
                ", name='" + name + '\'' +
                ", expenseCategory='" + expenseCategory + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", closingBalance=" + closingBalance +
                '}';
    }
}
