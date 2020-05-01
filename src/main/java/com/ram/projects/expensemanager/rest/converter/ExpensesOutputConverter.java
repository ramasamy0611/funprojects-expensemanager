package com.ram.projects.expensemanager.rest.converter;

import com.ram.projects.expensemanager.db.entity.ExpMgrExpense;
import com.ram.projects.expensemanager.rest.dto.Expense;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpensesOutputConverter implements Converter<List<ExpMgrExpense>, List<Expense>> {
  @Override
  public List<Expense> convert(List<ExpMgrExpense> input) {
    return input.stream()
        .map(expMgrExpense -> convertToExpense(expMgrExpense))
        .collect(Collectors.toList());
  }

  private Expense convertToExpense(ExpMgrExpense expMgrExpense) {
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
