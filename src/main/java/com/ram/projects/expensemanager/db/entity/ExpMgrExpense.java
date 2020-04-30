package com.ram.projects.expensemanager.db.entity;

import com.ram.projects.expensemanager.domain.expense.constants.ExpenseCategory;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "exp_mgr_expense")
public class ExpMgrExpense {
  @Id @GeneratedValue private Long Id;

  @Column(name = "transaction_date")
  private Timestamp transactionDate;

  @Column(name = "opening_balance")
  private Double openingBalance;

  @Column(name = "expense_name")
  private String expenseName;

  @Column(name = "exepense_category")
  @Enumerated(EnumType.STRING)
  private ExpenseCategory expenseCategory;

  @Column(name = "transaction_type")
  private String transactionType;

  @Column(name = "transaction_amount")
  private Double transactionAmount;

  @Column(name = "closing_balance")
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

  public String getExpenseName() {
    return expenseName;
  }

  public void setExpenseName(String expenseName) {
    this.expenseName = expenseName;
  }

  public ExpenseCategory getExpenseCategory() {
    return expenseCategory;
  }

  public void setExpenseCategory(ExpenseCategory expenseCategory) {
    this.expenseCategory = expenseCategory;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public Double getTransactionAmount() {
    return transactionAmount;
  }

  public void setTransactionAmount(Double amount) {
    this.transactionAmount = amount;
  }

  public Double getClosingBalance() {
    return closingBalance;
  }

  public void setClosingBalance(Double closingBalance) {
    this.closingBalance = closingBalance;
  }

  @Override
  public String toString() {
    return "Expense{"
        + "Id="
        + Id
        + ", transactionDate="
        + transactionDate
        + ", openingBalance="
        + openingBalance
        + ", name='"
        + expenseName
        + '\''
        + ", expenseCategory='"
        + expenseCategory
        + '\''
        + ", transactionType='"
        + transactionType
        + '\''
        + ", amount="
        + transactionAmount
        + ", closingBalance="
        + closingBalance
        + '}';
  }
}
