package com.ram.projects.expensemanager.rest.controller;

import com.ram.projects.expensemanager.db.entity.ExpMgrExpense;
import com.ram.projects.expensemanager.domain.expense.IExpenseManager;
import com.ram.projects.expensemanager.rest.converter.ExpenseInputConverter;
import com.ram.projects.expensemanager.rest.converter.ExpenseOutputConverter;
import com.ram.projects.expensemanager.rest.converter.ExpensesOutputConverter;
import com.ram.projects.expensemanager.rest.dto.Expense;
import com.ram.projects.expensemanager.rest.process.rest.RestProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public CompletableFuture<ResponseEntity<Expense>> addExpense(@RequestBody Expense expense) {
    return restProcessor.process(
        "Add a new Expense",
        expense,
        expenseInputConverter,
        expenseOutputConverter,
        expmgrExpense -> iExpenseManager.addExpense((ExpMgrExpense) expmgrExpense));
  }

  @GetMapping(path = "/getAllExpenses", produces = "application/json")
  public CompletableFuture<ResponseEntity<List<Expense>>> getAllExpenses() {
    return restProcessor.process(
        "Fetch all expenses",
        expensesOutputConverter,
        expMgrExpense -> iExpenseManager.getAllExpenses());
  }
}
