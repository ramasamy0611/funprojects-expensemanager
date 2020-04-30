package com.ram.projects.expensemanager.rest.converter;

import com.ram.projects.expensemanager.db.entity.ExpMgrExpense;
import com.ram.projects.expensemanager.rest.dto.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseOutputConverter implements Converter<ExpMgrExpense, Expense> {
  @Override
  public Expense convert(ExpMgrExpense expMgrExpense) {
    return new Expense(
        expMgrExpense.getTransactionDate().toInstant(),
        expMgrExpense.getOpeningBalance(),
        expMgrExpense.getExpenseName(),
        expMgrExpense.getExpenseCategory(),
        expMgrExpense.getTransactionType(),
        expMgrExpense.getTransactionAmount(),
        expMgrExpense.getClosingBalance());
  }
}
