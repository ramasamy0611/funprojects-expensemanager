package com.ram.projects.expensemanager.domain.expense;

import com.ram.projects.expensemanager.db.entity.ExpMgrExpense;
import com.ram.projects.expensemanager.db.repo.ExpenseRepository;
import com.ram.projects.expensemanager.rest.process.rest.RestProcessorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Service
public class ExpenseManagerImpl implements IExpenseManager {
  private static final Logger LOG = LoggerFactory.getLogger(ExpenseManagerImpl.class);
  private static final String LOG_HANDLE = "[ExpenseManagerImpl :]";
  private ExpenseRepository expenseRepository;
  private Executor executor = RestProcessorUtil.getExecutor();

  public ExpenseManagerImpl(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @Override
  public CompletableFuture<Long> addExpense(ExpMgrExpense expMgrExpenseToBoAdded) {
    LOG.debug(LOG_HANDLE + "Expense data to be added :{}", expMgrExpenseToBoAdded);
    return populateClosingAndOpeningBalanceFromLatest(expMgrExpenseToBoAdded)
        .thenCompose(expMgrExpenseUpdated -> addExpenseToDB(expMgrExpenseUpdated))
        .thenApply(expMgrExpense -> expMgrExpense.getId());
  }

  private CompletableFuture<ExpMgrExpense> addExpenseToDB(ExpMgrExpense expMgrExpenseUpdated) {
    return CompletableFuture.supplyAsync(
        () -> expenseRepository.save(expMgrExpenseUpdated), executor);
  }

  private CompletableFuture<ExpMgrExpense> populateClosingAndOpeningBalanceFromLatest(
      ExpMgrExpense expMgrExpenseToBeAdded) {
    return findAllExpenses()
        .thenApply(
            expensesFromDB ->
                updateClosingBalanceBasedOnLastExpense(expMgrExpenseToBeAdded, expensesFromDB));
  }

  private CompletableFuture<List<ExpMgrExpense>> findAllExpenses() {
    return CompletableFuture.supplyAsync(() -> expenseRepository.findAll(), executor);
  }

  private ExpMgrExpense updateClosingBalanceBasedOnLastExpense(
      ExpMgrExpense expMgrExpenseToBeAdded, List<ExpMgrExpense> expensesFromDB) {
    return expensesFromDB.stream()
        .max(Comparator.comparing(ExpMgrExpense::getTransactionDate))
        .map(
            expMgrExpense ->
                populateClosingAndOpeningBalance(expMgrExpense, expMgrExpenseToBeAdded))
        .orElse(populateClosingAndOpeningBalanceFromCurrentData(expMgrExpenseToBeAdded));
  }

  private ExpMgrExpense populateClosingAndOpeningBalanceFromCurrentData(
      ExpMgrExpense expMgrExpenseToBeAdded) {
    expMgrExpenseToBeAdded.setClosingBalance(
        expMgrExpenseToBeAdded.getOpeningBalance() - expMgrExpenseToBeAdded.getTransactionAmount());
    expMgrExpenseToBeAdded.setOpeningBalance(expMgrExpenseToBeAdded.getClosingBalance());
    return expMgrExpenseToBeAdded;
  }

  private ExpMgrExpense populateClosingAndOpeningBalance(
      ExpMgrExpense expMgrExpense, ExpMgrExpense expMgrExpenseToBeAdded) {
    ExpMgrExpense expMgrExpenseUpdated = expMgrExpenseToBeAdded;
    expMgrExpenseUpdated.setClosingBalance(
        expMgrExpense.getClosingBalance() - expMgrExpenseToBeAdded.getTransactionAmount());
    expMgrExpenseUpdated.setOpeningBalance(expMgrExpense.getClosingBalance());
    return expMgrExpenseUpdated;
  }

  @Override
  public CompletableFuture<ExpMgrExpense> getExpenseById(Long expenseId) {
    return CompletableFuture.supplyAsync(() -> expenseRepository.getOne(expenseId), executor);
  }

  @Override
  public CompletableFuture<List<ExpMgrExpense>> getAllExpenses() {
    return CompletableFuture.supplyAsync(() -> expenseRepository.findAll(), executor);
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
