package com.ram.projects.expensemanager.db.repo;

import com.ram.projects.expensemanager.db.entity.ExpMgrExpense;
import com.ram.projects.expensemanager.domain.expense.constants.ExpenseCategory;
import com.ram.projects.expensemanager.domain.expense.constants.ExpenseName;
import com.ram.projects.expensemanager.domain.expense.constants.TransactionSource;
import com.ram.projects.expensemanager.domain.expense.constants.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpMgrExpense, Long> {
  Optional<List<ExpMgrExpense>> findByTransactionDate(@Param("transactionDate") Timestamp instant);

  Optional<List<ExpMgrExpense>> findAllByTransactionDateBetween(
      Timestamp fromDate, Timestamp toDate);

  Optional<List<ExpMgrExpense>> findAllByTransactionType(
      TransactionType transactionType);

  Optional<List<ExpMgrExpense>> findAllByTransactionSource(
      TransactionSource transactionSource);

  Optional<List<ExpMgrExpense>> findAllByExpenseCategory(ExpenseCategory expenseCategory);

  Optional<List<ExpMgrExpense>> findAllByExpenseCategoryAndExpenseName(
      ExpenseCategory expenseCategory, ExpenseName expenseName);
}
