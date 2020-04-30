package com.ram.projects.expensemanager.domain.expense;

import com.ram.projects.expensemanager.db.entity.ExpMgrExpense;
import com.ram.projects.expensemanager.db.repo.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseManagerImpl implements IExpenseManager {
  private ExpenseRepository expenseRepository;

  public ExpenseManagerImpl(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @Override
  public Long addExpense(ExpMgrExpense expMgrExpense) {
    return expenseRepository.save(expMgrExpense).getId();
  }

  @Override
  public ExpMgrExpense getExpenseById(Long expenseId) {
    return expenseRepository.getOne(expenseId);
  }

  @Override
  public Long updateExpense(ExpMgrExpense expMgrExpense) {
    return expenseRepository.save(expMgrExpense).getId();
  }

  @Override
  public List<Long> updateExpense(List<ExpMgrExpense> expMgrExpenses) {
    return expenseRepository
        .saveAll(expMgrExpenses)
        .parallelStream()
        .map(expMgrExpense -> expMgrExpense.getId())
        .collect(Collectors.toList());
  }

  @Override
  public Long deleteExpense(ExpMgrExpense expMgrExpense) {
    return null;
  }
}
