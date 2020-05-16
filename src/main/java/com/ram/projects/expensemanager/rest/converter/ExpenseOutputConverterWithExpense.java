package com.ram.projects.expensemanager.rest.converter;

import com.ram.projects.expensemanager.db.entity.ExpMgrExpense;
import com.ram.projects.expensemanager.rest.dto.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseOutputConverterWithExpense implements Converter<ExpMgrExpense, Expense> {
  @Override
  public Expense convert(ExpMgrExpense expMgrExpense) {
    Expense expense = new Expense();
    expense.setOpeningBalance(expMgrExpense.getOpeningBalance());
    expense.setExpenseCategory(expMgrExpense.getExpenseCategory());
    expense.setExpenseName(expMgrExpense.getExpenseName());
    expense.setClosingBalance(expMgrExpense.getClosingBalance());
    expense.setTransactionAmount(expMgrExpense.getTransactionAmount());
    expense.setTransactionDate(expMgrExpense.getTransactionDate().toInstant());
    expense.setTransactionType(expMgrExpense.getTransactionType());
    expense.setTransactionSource(expMgrExpense.getTransactionSource());
    return expense;
  }
}
