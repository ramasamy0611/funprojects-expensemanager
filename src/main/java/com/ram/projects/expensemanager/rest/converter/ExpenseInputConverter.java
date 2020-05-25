package com.ram.projects.expensemanager.rest.converter;

import com.ram.projects.expensemanager.db.entity.ExpMgrExpense;
import com.ram.projects.expensemanager.rest.dto.Expense;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;

@Component
public class ExpenseInputConverter implements Converter<Expense, ExpMgrExpense> {
  @Override
  public ExpMgrExpense convert(Expense expenseToBeAdded) {
    ExpMgrExpense expMgrExpense = new ExpMgrExpense();
    expMgrExpense.setOpeningBalance(expenseToBeAdded.getOpeningBalance());
    expMgrExpense.setExpenseCategory(expenseToBeAdded.getExpenseCategory());
    expMgrExpense.setExpenseName(expenseToBeAdded.getExpenseName());
    expMgrExpense.setClosingBalance(expenseToBeAdded.getClosingBalance());
    expMgrExpense.setTransactionAmount(expenseToBeAdded.getTransactionAmount());
    expMgrExpense.setTransactionDate(
        Timestamp.from(expenseToBeAdded.getTransactionDate().truncatedTo(ChronoUnit.HOURS)));
    expMgrExpense.setTransactionType(expenseToBeAdded.getTransactionType());
    expMgrExpense.setTransactionSource(expenseToBeAdded.getTransactionSource());
    return expMgrExpense;
  }
}
