package com.ram.projects.expensemanager.domain.user;

import com.ram.projects.expensemanager.db.entity.ExpMgrUser;
import com.ram.projects.expensemanager.db.repo.UserRepository;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class UserManager implements IUserManager {
    private UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<ExpMgrUser> addUser(ExpMgrUser expMgrUser) {
        validateUserData(expMgrUser);
        expMgrUser.setCreationDate(new Timestamp(Instant.now().toEpochMilli()));
        expMgrUser.setModifiedDate(new Timestamp(Instant.now().toEpochMilli()));
        expMgrUser.setComment("CREATED");
        return CompletableFuture.supplyAsync(() -> userRepository.save(expMgrUser));
    }

    private void validateUserData(ExpMgrUser expMgrUser) {
        if (expMgrUser == null) {
            handleIllegalArguments("Missing user object", null);
        }
        if (expMgrUser.getFirstName() == null || expMgrUser.getLastName() == null || expMgrUser.geteMail() == null) {
            handleIllegalArguments("Missing FirstName or LastName or Email", null);
        }
    }
    private void handleIllegalArguments(String message, Object attributeName){
        throw new IllegalArgumentException(String.format((message), attributeName));
    }

    @Override
    public CompletableFuture<ExpMgrUser> addUser(List<ExpMgrUser> expMgrUser) {
        return null;
    }

    @Override
    public CompletableFuture<ExpMgrUser> updateUser(ExpMgrUser expMgrUser) {
        return null;
    }

    @Override
    public CompletableFuture<ExpMgrUser> deleteUser(ExpMgrUser expMgrUser) {
        return null;
    }

    @Override
    public CompletableFuture<ExpMgrUser> deleteUser(List<ExpMgrUser> expMgrUser) {
        return null;
    }
}
