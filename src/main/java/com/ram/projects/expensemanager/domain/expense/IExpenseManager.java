package com.ram.projects.expensemanager.domain.expense;

import com.ram.projects.expensemanager.db.entity.Expense;

import java.util.List;

public interface IExpenseManager {
  Integer addExpense(Expense expense);

  Expense getExpenseById(Integer expenseId);

  Integer updateExpense(Expense expense);

  List<Integer> updateExpense(List<Expense> expense);

  Integer deleteExpense(Expense expense);
}
