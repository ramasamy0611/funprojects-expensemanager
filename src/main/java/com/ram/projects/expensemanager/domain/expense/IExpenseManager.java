package com.ram.projects.expensemanager.domain.expense;

import com.ram.projects.expensemanager.db.entity.ExpMgrExpense;

import java.util.List;

public interface IExpenseManager {
  Long addExpense(ExpMgrExpense expMgrExpense);

  ExpMgrExpense getExpenseById(Long expenseId);

  Long updateExpense(ExpMgrExpense expMgrExpense);

  List<Long> updateExpense(List<ExpMgrExpense> expMgrExpense);

  Long deleteExpense(ExpMgrExpense expMgrExpense);
}
