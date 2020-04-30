package com.ram.projects.expensemanager.rest.dto;

import com.ram.projects.expensemanager.domain.expense.constants.ExpenseCategory;

import java.time.Instant;
import java.util.Date;

import static com.ram.projects.expensemanager.common.CommonUtils.validateIfNull;

public class Expense {
  private Instant transactionDate;

  private Double openingBalance;

  private String expenseName;

  private ExpenseCategory expenseCategory;

  private String transactionType;

  private Double transactionAmount;

  private Double closingBalance;

  public Expense(
      Instant transactionDate,
      Double openingBalance,
      String expenseName,
      ExpenseCategory expenseCategory,
      String transactionType,
      Double transactionAmount,
      Double closingBalance) {
    this.transactionDate = transactionDate;
    this.openingBalance = openingBalance;
    this.expenseName = expenseName;
    this.expenseCategory = expenseCategory;
    this.transactionType = transactionType;
    this.transactionAmount = transactionAmount;
    this.closingBalance = closingBalance;
  }

  public Expense() {}

  public Date getTransactionDate() {
    return new Date(transactionDate.toEpochMilli());
  }

  public Instant getTransactionTime() {
    return transactionDate;
  }

  public void setTransactionTime(Instant transactionDate) {
    this.transactionDate = transactionDate;
  }

  public void setTransactionDate(Date transactionDate) {
    validateIfNull(transactionDate);
    this.transactionDate = transactionDate.toInstant();
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

  public void setTransactionAmount(Double transactionAmount) {
    this.transactionAmount = transactionAmount;
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
        + "transactionDate="
        + transactionDate
        + ", openingBalance="
        + openingBalance
        + ", expenseName='"
        + expenseName
        + '\''
        + ", expenseCategory='"
        + expenseCategory
        + '\''
        + ", transactionType='"
        + transactionType
        + '\''
        + ", transactionAmount="
        + transactionAmount
        + ", closingBalance="
        + closingBalance
        + '}';
  }
}
