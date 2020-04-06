package com.ram.projects.expensemanager.domain.user;

import com.ram.projects.expensemanager.db.entity.ExpMgrUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface IUserManager {
    CompletableFuture<ExpMgrUser> addUser(ExpMgrUser expMgrUser);

    CompletableFuture<ExpMgrUser> addUser(List<ExpMgrUser> expMgrUser);

    CompletableFuture<ExpMgrUser> updateUser(ExpMgrUser expMgrUser);

    CompletableFuture<ExpMgrUser> deleteUser(ExpMgrUser expMgrUser);

    CompletableFuture<ExpMgrUser> deleteUser(List<ExpMgrUser> expMgrUser);
}
