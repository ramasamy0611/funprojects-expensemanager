package com.ram.projects.expensemanager.domain.expense;

import com.ram.projects.expensemanager.db.entity.ExpMgrExpense;
import com.ram.projects.expensemanager.db.repo.ExpenseRepository;
import com.ram.projects.expensemanager.domain.expense.constants.ExpenseCategory;
import com.ram.projects.expensemanager.domain.expense.constants.ExpenseName;
import com.ram.projects.expensemanager.domain.expense.constants.TransactionSource;
import com.ram.projects.expensemanager.domain.expense.constants.TransactionType;
import com.ram.projects.expensemanager.exception.ExpenseNotFoundException;
import com.ram.projects.expensemanager.rest.process.rest.RestProcessorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Service
public class ExpenseManagerImpl implements IExpenseManager {
  private static final Logger LOG = LoggerFactory.getLogger(ExpenseManagerImpl.class);
  private static final String LOG_HANDLE = "[ExpenseManagerImpl :] ";
  private ExpenseRepository expenseRepository;
  private Executor executor = RestProcessorUtil.getExecutor();

  @Cacheable(cacheNames = "expenseall-cache")
  @Override
  public CompletableFuture<Long> addExpense(ExpMgrExpense expMgrExpenseToBoAdded) {
    LOG.debug(LOG_HANDLE + "Expense data to be added :{}", expMgrExpenseToBoAdded);
    return populateClosingAndOpeningBalanceFromLatest(expMgrExpenseToBoAdded)
            .thenCompose(expMgrExpenseUpdated -> addExpenseToDB(expMgrExpenseUpdated))
            .thenApply(expMgrExpense -> expMgrExpense.getId());
  }
  public ExpenseManagerImpl(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @Override
  public CompletableFuture<ExpMgrExpense> addGetExpense(ExpMgrExpense expMgrExpense) {
    LOG.debug(LOG_HANDLE + "Expense data to be added :{}", expMgrExpense);
    return populateClosingAndOpeningBalanceFromLatest(expMgrExpense)
        .thenCompose(expMgrExpenseUpdated -> addExpenseToDB(expMgrExpenseUpdated));
  }

  @Override
  public CompletableFuture<List<Long>> addExpense(List<ExpMgrExpense> expMgrExpensesToBeAdded) {
    return addExpenses(expMgrExpensesToBeAdded)
        .thenApply(expMgrExpenses -> extractExpenseIds(expMgrExpenses));
  }

  private List<Long> extractExpenseIds(List<ExpMgrExpense> expMgrExpenses) {
    return expMgrExpenses
        .parallelStream()
        .map(expMgrExpense -> expMgrExpense.getId())
        .collect(Collectors.toList());
  }

  private CompletableFuture<List<ExpMgrExpense>> addExpenses(List<ExpMgrExpense> expMgrExpenses) {
    return CompletableFuture.supplyAsync(() -> expenseRepository.saveAll(expMgrExpenses), executor);
  }

  @Override
  public CompletableFuture<ExpMgrExpense> getExpenseById(Long expenseId) {
    return CompletableFuture.supplyAsync(() -> expenseRepository.getOne(expenseId), executor);
  }
  @Cacheable(cacheNames = "expenseall-cache")
  @Override
  public CompletableFuture<List<ExpMgrExpense>> getAllExpenses() {
    LOG.debug("Fetching from DB!");
    return CompletableFuture.supplyAsync(() -> expenseRepository.findAll(), executor);
  }

  @Override
  public CompletableFuture<List<ExpMgrExpense>> getExpensesByTransactionDate(Instant instant) {
    LOG.debug("getExpensesByTransactionDate date instant :{}", instant);
    return CompletableFuture.supplyAsync(
        () ->
            expenseRepository
                .findAllByTransactionDateBetween(Timestamp.from(instant), Timestamp.from(instant))
                .orElseThrow(ExpenseNotFoundException::new),
        executor);
  }

  @Override
  public CompletableFuture<List<ExpMgrExpense>> getExpensesBetweenTransactionDates(
      Instant dateFrom, Instant dateTo) {
    return CompletableFuture.supplyAsync(
        () ->
            expenseRepository
                .findAllByTransactionDateBetween(Timestamp.from(dateFrom), Timestamp.from(dateTo))
                .orElseThrow(ExpenseNotFoundException::new),
        executor);
  }

  @Override
  public CompletableFuture<List<ExpMgrExpense>> getExpensesByTransactionType(
      TransactionType transactionType) {
    return CompletableFuture.supplyAsync(
        () ->
            expenseRepository
                .findAllByTransactionType(transactionType)
                .orElseThrow(ExpenseNotFoundException::new),
        executor);
  }

  @Override
  public CompletableFuture<List<ExpMgrExpense>> getExpensesByTransactionSource(
      TransactionSource transactionSource) {
    return CompletableFuture.supplyAsync(
        () ->
            expenseRepository
                .findAllByTransactionSource(transactionSource)
                .orElseThrow(ExpenseNotFoundException::new),
        executor);
  }

  @Override
  public CompletableFuture<List<ExpMgrExpense>> getExpensesByCategoryAndName(
      ExpenseCategory expenseCategory, ExpenseName expenseName) {
    return CompletableFuture.supplyAsync(
        () ->
            expenseRepository
                .findAllByExpenseCategoryAndExpenseName(expenseCategory, expenseName)
                .orElseThrow(ExpenseNotFoundException::new),
        executor);
  }

  @Override
  public CompletableFuture<List<ExpMgrExpense>> getExpensesByCategory(
      ExpenseCategory expenseCategory) {
    return CompletableFuture.supplyAsync(
        () ->
            expenseRepository
                .findAllByExpenseCategory(expenseCategory)
                .orElseThrow(ExpenseNotFoundException::new),
        executor);
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
    expMgrExpenseToBeAdded.setOpeningBalance(expMgrExpenseToBeAdded.getClosingBalance());
    expMgrExpenseToBeAdded.setClosingBalance(
        expMgrExpenseToBeAdded.getOpeningBalance() - expMgrExpenseToBeAdded.getTransactionAmount());
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
}
