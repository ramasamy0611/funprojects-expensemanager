package com.ram.projects.expensemanager.domain.user;

import com.ram.projects.expensemanager.db.entity.ExpMgrUser;
import com.ram.projects.expensemanager.db.repo.UserRepository;
import com.ram.projects.expensemanager.exception.ExpMgrException;
import com.ram.projects.expensemanager.exception.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class UserManager implements IUserManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManager.class);
    private final UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<ExpMgrUser> addUser(ExpMgrUser expMgrUser) {
        return CompletableFuture.supplyAsync(() -> validateUserData(expMgrUser))
                .thenApply(noData -> populateDatesAndComment(expMgrUser))
                .thenApply(expMgrUserUpdated -> userRepository.save(expMgrUserUpdated))
                .exceptionally(throwable -> handleFailure(throwable, expMgrUser));
    }

    private ExpMgrUser populateDatesAndComment(ExpMgrUser expMgrUser) {
        expMgrUser.setCreationDate(new Timestamp(Instant.now().toEpochMilli()));
        expMgrUser.setModifiedDate(new Timestamp(Instant.now().toEpochMilli()));
        expMgrUser.setComment("CREATED");
        return expMgrUser;
    }

    private ExpMgrUser handleFailure(Throwable throwable, ExpMgrUser expMgrUser) {
        if (throwable.getCause() instanceof UserAlreadyExistsException) {
            UserAlreadyExistsException userAlreadyExistsException = (UserAlreadyExistsException) throwable.getCause();
            return userAlreadyExistsException.getExistingUser();
        }
        LOGGER.error("Failed to add User {}, reason {}", expMgrUser, throwable.getMessage());
        throw new ExpMgrException(String.format("Failed to add User :{}, reason :{}", expMgrUser, throwable.getMessage()));
    }

    private ExpMgrUser validateUserData(ExpMgrUser expMgrUser) {
        if (expMgrUser == null) {
            handleIllegalArguments("Missing user object", null);
        }
        userRepository.findByFirstNameAndLastNameAndEmail(expMgrUser.getFirstName(), expMgrUser.getLastName(), expMgrUser.geteEmail())
                .ifPresent(expMgrUserFromDB -> throwUserExistsException(expMgrUserFromDB));

        if (expMgrUser.getFirstName() == null || expMgrUser.getLastName() == null || expMgrUser.geteEmail() == null || expMgrUser.getType() == null) {
            handleIllegalArguments(String.format("Missing FirstName :{}, or LastName :{}, or Email :{}, or type :{}", expMgrUser.getFirstName(), expMgrUser.getLastName(), expMgrUser.geteEmail(), expMgrUser.getType()), null);
        }
        return expMgrUser;
    }

    private ExpMgrUser throwUserExistsException(ExpMgrUser expMgrUserFromDB) {
        throw new UserAlreadyExistsException("User already exists in the system", expMgrUserFromDB);
    }

    private void handleIllegalArguments(String message, Object attributeName) {
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
