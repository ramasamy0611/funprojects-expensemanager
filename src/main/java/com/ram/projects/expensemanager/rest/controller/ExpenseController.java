package com.ram.projects.expensemanager.rest.controller;

import com.ram.projects.expensemanager.db.entity.ExpMgrExpense;
import com.ram.projects.expensemanager.domain.expense.IExpenseManager;
import com.ram.projects.expensemanager.rest.converter.ExpenseInputConverter;
import com.ram.projects.expensemanager.rest.converter.ExpenseOutputConverter;
import com.ram.projects.expensemanager.rest.dto.Expense;
import com.ram.projects.expensemanager.rest.process.rest.RestProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

import static com.ram.projects.expensemanager.common.Constants.ROOT_END_POINT;

@RestController
@RequestMapping(ROOT_END_POINT + "/expense")
public class ExpenseController {
  private final RestProcessor restProcessor;
  private final IExpenseManager iExpenseManager;
  private final ExpenseInputConverter expenseInputConverter;
  private final ExpenseOutputConverter expenseOutputConverter;

  public ExpenseController(
      IExpenseManager iExpenseManager,
      RestProcessor restProcessor,
      ExpenseInputConverter expenseInputConverter,
      ExpenseOutputConverter expenseOutputConverter) {
    this.iExpenseManager = iExpenseManager;
    this.restProcessor = restProcessor;
    this.expenseInputConverter = expenseInputConverter;
    this.expenseOutputConverter = expenseOutputConverter;
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
}
