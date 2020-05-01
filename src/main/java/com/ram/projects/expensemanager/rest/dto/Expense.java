package com.ram.projects.expensemanager.rest.dto;

import com.ram.projects.expensemanager.domain.expense.constants.ExpenseCategory;
import com.ram.projects.expensemanager.domain.expense.constants.ExpenseName;
import com.ram.projects.expensemanager.domain.expense.constants.TransactionSource;
import com.ram.projects.expensemanager.domain.expense.constants.TransactionType;

import java.time.Instant;

import static com.ram.projects.expensemanager.common.CommonUtils.validateIfNull;

public class Expense {
  private Instant transactionDate;

  private Double openingBalance;

  private ExpenseName expenseName;

  private ExpenseCategory expenseCategory;

  private TransactionType transactionType;

  private TransactionSource transactionSource;

  private Double transactionAmount;

  private Double closingBalance;

  public Expense(
      Instant transactionDate,
      Double openingBalance,
      ExpenseName expenseName,
      ExpenseCategory expenseCategory,
      TransactionType transactionType,
      TransactionSource transactionSource,
      Double transactionAmount,
      Double closingBalance) {
    this.transactionDate = transactionDate;
    this.openingBalance = openingBalance;
    this.expenseName = expenseName;
    this.expenseCategory = expenseCategory;
    this.transactionType = transactionType;
    this.transactionSource = transactionSource;
    this.transactionAmount = transactionAmount;
    this.closingBalance = closingBalance;
  }

  public Expense() {}

  public Instant getTransactionDate() {
    if (this.transactionDate == null) {
      return Instant.now();
    }
    return this.transactionDate;
  }

  public void setTransactionDate(Instant transactionDate) {
    this.transactionDate = transactionDate;
  }

  public Double getOpeningBalance() {
    return openingBalance;
  }

  public void setOpeningBalance(Double openingBalance) {
    this.openingBalance = openingBalance;
  }

  public ExpenseName getExpenseName() {
    return expenseName;
  }

  public void setExpenseName(ExpenseName expenseName) {
    this.expenseName = expenseName;
  }

  public ExpenseCategory getExpenseCategory() {
    return expenseCategory;
  }

  public void setExpenseCategory(ExpenseCategory expenseCategory) {
    this.expenseCategory = expenseCategory;
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }

  public Double getTransactionAmount() {
    validateIfNull(this.transactionAmount);
    return transactionAmount;
  }

  public void setTransactionAmount(Double transactionAmount) {
    this.transactionAmount = transactionAmount;
  }

  public Double getClosingBalance() {
    if (this.closingBalance == null){
      this.closingBalance = 0D;
    }
    return this.closingBalance;
  }

  public void setClosingBalance(Double closingBalance) {
    this.closingBalance = closingBalance;
  }

  public TransactionSource getTransactionSource() {
    return transactionSource;
  }

  public void setTransactionSource(TransactionSource transactionSource) {
    this.transactionSource = transactionSource;
  }

  @Override
  public String toString() {
    return "Expense{"
        + "transactionDate="
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
