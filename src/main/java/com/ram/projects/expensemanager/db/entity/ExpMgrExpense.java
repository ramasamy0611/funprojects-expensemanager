package com.ram.projects.expensemanager.db.entity;

import com.ram.projects.expensemanager.domain.expense.constants.ExpenseCategory;
import com.ram.projects.expensemanager.domain.expense.constants.ExpenseName;
import com.ram.projects.expensemanager.domain.expense.constants.TransactionSource;
import com.ram.projects.expensemanager.domain.expense.constants.TransactionType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "exp_mgr_expense")
public class ExpMgrExpense {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  @Column(name = "transaction_date")
  private Timestamp transactionDate;

  @Column(name = "opening_balance")
  private Double openingBalance;

  @Column(name = "expense_name")
  @Enumerated(EnumType.STRING)
  private ExpenseName expenseName;

  @Column(name = "expense_category")
  @Enumerated(EnumType.STRING)
  private ExpenseCategory expenseCategory;

  @Column(name = "transaction_type")
  @Enumerated(EnumType.STRING)
  private TransactionType transactionType;

  @Column(name = "transaction_source")
  @Enumerated(EnumType.STRING)
  private TransactionSource transactionSource;

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

  public ExpenseCategory getExpenseCategory() {
    return expenseCategory;
  }

  public void setExpenseCategory(ExpenseCategory expenseCategory) {
    this.expenseCategory = expenseCategory;
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

  public ExpenseName getExpenseName() {
    return expenseName;
  }

  public void setExpenseName(ExpenseName expenseName) {
    this.expenseName = expenseName;
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }

  public TransactionSource getTransactionSource() {
    return transactionSource;
  }

  public void setTransactionSource(TransactionSource transactionSource) {
    this.transactionSource = transactionSource;
  }

  @Override
  public String toString() {
    return "ExpMgrExpense{"
        + "Id="
        + Id
        + ", transactionDate="
        + transactionDate
        + ", openingBalance="
        + openingBalance
        + ", expenseName="
        + expenseName
        + ", expenseCategory="
        + expenseCategory
        + ", transactionType="
        + transactionType
        + ", transactionSource="
        + transactionSource
        + ", transactionAmount="
        + transactionAmount
        + ", closingBalance="
        + closingBalance
        + '}';
  }
}
