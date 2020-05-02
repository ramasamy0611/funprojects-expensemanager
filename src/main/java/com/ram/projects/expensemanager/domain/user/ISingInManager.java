package com.ram.projects.expensemanager.domain.user;

import com.ram.projects.expensemanager.db.entity.ExpMgrSignIn;
import com.ram.projects.expensemanager.db.entity.ExpMgrSignUp;
import com.ram.projects.expensemanager.rest.dto.SignUp;

import java.util.concurrent.CompletableFuture;

public interface ISingInManager {
  CompletableFuture<ExpMgrSignIn> signIn(ExpMgrSignIn signIn);

  CompletableFuture<String> signUp(ExpMgrSignUp expMgrSignUp);
}
