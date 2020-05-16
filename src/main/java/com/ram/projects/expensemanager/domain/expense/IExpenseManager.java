package com.ram.projects.expensemanager.domain.expense;

import com.ram.projects.expensemanager.db.entity.ExpMgrExpense;
import com.ram.projects.expensemanager.domain.expense.constants.ExpenseCategory;
import com.ram.projects.expensemanager.domain.expense.constants.ExpenseName;
import com.ram.projects.expensemanager.domain.expense.constants.TransactionSource;
import com.ram.projects.expensemanager.domain.expense.constants.TransactionType;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IExpenseManager {
  CompletableFuture<Long> addExpense(ExpMgrExpense expMgrExpense);
  CompletableFuture<ExpMgrExpense> addGetExpense(ExpMgrExpense expMgrExpense);

  CompletableFuture<List<Long>> addExpense(List<ExpMgrExpense> expMgrExpense);

  CompletableFuture<ExpMgrExpense> getExpenseById(Long expenseId);

  CompletableFuture<List<ExpMgrExpense>> getAllExpenses();

  CompletableFuture<List<ExpMgrExpense>> getExpensesByTransactionDate(Instant instant);

  CompletableFuture<List<ExpMgrExpense>> getExpensesBetweenTransactionDates(Instant dateFrom, Instant dateTo);

  CompletableFuture<List<ExpMgrExpense>> getExpensesByTransactionType(TransactionType transactionType);

  CompletableFuture<List<ExpMgrExpense>> getExpensesByTransactionSource(TransactionSource transactionSource);

  CompletableFuture<List<ExpMgrExpense>> getExpensesByCategoryAndName(ExpenseCategory expenseCategory, ExpenseName expenseName);

  CompletableFuture<List<ExpMgrExpense>> getExpensesByCategory(ExpenseCategory expenseCategory);
}
