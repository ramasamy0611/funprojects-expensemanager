package com.ram.projects.expensemanager.rest.controller;

import com.ram.projects.expensemanager.db.entity.ExpMgrExpense;
import com.ram.projects.expensemanager.domain.expense.IExpenseManager;
import com.ram.projects.expensemanager.domain.expense.constants.ExpenseCategory;
import com.ram.projects.expensemanager.domain.expense.constants.ExpenseName;
import com.ram.projects.expensemanager.rest.RestResponse;
import com.ram.projects.expensemanager.rest.converter.ExpenseInputConverter;
import com.ram.projects.expensemanager.rest.converter.ExpenseOutputConverter;
import com.ram.projects.expensemanager.rest.converter.ExpensesOutputConverter;
import com.ram.projects.expensemanager.rest.dto.Expense;
import com.ram.projects.expensemanager.rest.process.rest.RestProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.ram.projects.expensemanager.common.Constants.ROOT_END_POINT;

@RestController
@RequestMapping(ROOT_END_POINT + "/expense")
public class ExpenseController {
  private final RestProcessor restProcessor;
  private final IExpenseManager iExpenseManager;
  private final ExpenseInputConverter expenseInputConverter;
  private final ExpenseOutputConverter expenseOutputConverter;
  private final ExpensesOutputConverter expensesOutputConverter;

  public ExpenseController(
      IExpenseManager iExpenseManager,
      RestProcessor restProcessor,
      ExpenseInputConverter expenseInputConverter,
      ExpenseOutputConverter expenseOutputConverter,
      ExpensesOutputConverter expensesOutputConverter) {
    this.iExpenseManager = iExpenseManager;
    this.restProcessor = restProcessor;
    this.expenseInputConverter = expenseInputConverter;
    this.expenseOutputConverter = expenseOutputConverter;
    this.expensesOutputConverter = expensesOutputConverter;
  }

  @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
  public CompletableFuture<ResponseEntity<RestResponse<Long>>> addExpense(@RequestBody Expense expense) {
    return restProcessor.process(
        "Add a new Expense",
        expense,
        expenseInputConverter,
        expenseOutputConverter,
        expmgrExpense -> iExpenseManager.addExpense((ExpMgrExpense) expmgrExpense));
  }

  @GetMapping(path = "/getAllExpenses", produces = "application/json")
  public CompletableFuture<ResponseEntity<RestResponse<List<Expense>>>> getAllExpenses() {
    return restProcessor.process(
        "Fetch all expenses",
        expensesOutputConverter,
        expMgrExpense -> iExpenseManager.getAllExpenses());
  }

  @GetMapping(path = "/getAllExpensesBetweenDates/{fromDate}/{toDate}", produces = "application/json")
  public CompletableFuture<ResponseEntity<List<Expense>>> getExpensesBetweenTransactionDates(
      @PathVariable("fromDate") Instant fromDate, @PathVariable("toDate") Instant toDate) {
    return restProcessor.process(
        "getExpensesBetweenTransactionDates",
        expensesOutputConverter,
        expMgrExpense -> iExpenseManager.getExpensesBetweenTransactionDates(fromDate, toDate));
  }

  @GetMapping(path = "/getAllExpensesByDate/{aDate}", produces = "application/json")
  public CompletableFuture<ResponseEntity<List<Expense>>> getExpensesByTransactionDate(
      @PathVariable("aDate") Instant aDate) {
    return restProcessor.process(
        "getExpensesByTransactionDate",
        expensesOutputConverter,
        expMgrExpense -> iExpenseManager.getExpensesByTransactionDate(aDate));
  }

  @GetMapping(path = "/getAllExpensesByCategory/{expenseCategory}", produces = "application/json")
  public CompletableFuture<ResponseEntity<List<Expense>>> getExpensesByCategory(
      @PathVariable("expenseCategory") ExpenseCategory expenseCategory) {
    return restProcessor.process(
        "Fetch all expenses by ExpenseCategory",
        expensesOutputConverter,
        expMgrExpense -> iExpenseManager.getExpensesByCategory(expenseCategory));
  }

  @GetMapping(
      path = "/getAllExpenses/{expenseCategory}/{expenseName}",
      produces = "application/json")
  public CompletableFuture<ResponseEntity<List<Expense>>> getExpensesByCategoryAndName(
      @PathVariable("expenseCategory") ExpenseCategory expenseCategory,
      @PathVariable("expenseName") ExpenseName expenseName) {
    return restProcessor.process(
        "Fetch all expenses",
        expensesOutputConverter,
        expMgrExpense ->
            iExpenseManager.getExpensesByCategoryAndName(expenseCategory, expenseName));
  }
}
