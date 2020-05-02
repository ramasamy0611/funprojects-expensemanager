package com.ram.projects.expensemanager.domain.user;

import com.ram.projects.expensemanager.db.entity.ExpMgrUser;
import com.ram.projects.expensemanager.db.repo.UserRepository;
import com.ram.projects.expensemanager.exception.ExpMgrException;
import com.ram.projects.expensemanager.exception.PreRequisiteFailedException;
import com.ram.projects.expensemanager.exception.UserAlreadyExistsException;
import com.ram.projects.expensemanager.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class UserManagerImpl implements IUserManager {
  private static final Logger LOG = LoggerFactory.getLogger(UserManagerImpl.class);
  private static final String LOG_HANDLE = "[UserManager :] ";
  private final UserRepository userRepository;

  public UserManagerImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public CompletableFuture<ExpMgrUser> addUser(ExpMgrUser expMgrUser) {
    return CompletableFuture.supplyAsync(() -> validateIfUserNotAlreadyExists(expMgrUser))
        .thenApply(noData -> populateDatesAndComment(expMgrUser))
        .thenApply(userRepository::save)
        .exceptionally(throwable -> handleFailure(throwable, expMgrUser));
  }

  @Override
  public CompletableFuture<List<ExpMgrUser>> addUser(List<ExpMgrUser> expMgrUsers) {
    return CompletableFuture.supplyAsync(() -> validateIfUserNotAlreadyExists(expMgrUsers))
        .thenApply(this::populateDatesAndComment)
        .thenApply(users -> userRepository.saveAll(users))
        .exceptionally(throwable -> handleFailure(throwable, expMgrUsers));
  }

  @Override
  public CompletableFuture<ExpMgrUser> updateUser(ExpMgrUser expMgrUser) {
    return null;
  }

  @Override
  public CompletableFuture<Long> deleteUser(ExpMgrUser expMgrUserToBeDeleted) {
    return CompletableFuture.supplyAsync(() -> validateIfUserExists(expMgrUserToBeDeleted))
        .thenCompose(this::deleteUserFromDB)
        .exceptionally(
            throwable -> handleFailureForDeleteUser(throwable, expMgrUserToBeDeleted.getId()));
  }

  private Long handleFailureForDeleteUser(Throwable throwable, Long userId) {
    if (throwable instanceof UserNotFoundException) {
      LOG.error(
          LOG_HANDLE + "User Id :{}, doesn't exists to delete exception :{} ", userId, throwable);
      throw (UserNotFoundException) throwable;
    }
    return userId;
  }

  private CompletableFuture<Long> deleteUserFromDB(ExpMgrUser expMgrUser) {
    return CompletableFuture.runAsync(() -> deleteUserFromDB(expMgrUser))
        .thenApply(noData -> expMgrUser.getId());
  }

  @Override
  public CompletableFuture<List<ExpMgrUser>> getAllUsers() {
    return CompletableFuture.supplyAsync(() -> userRepository.findAll());
  }

  private ExpMgrUser populateDatesAndComment(ExpMgrUser expMgrUser) {
    expMgrUser.setCreationDate(new Timestamp(Instant.now().toEpochMilli()));
    expMgrUser.setModifiedDate(new Timestamp(Instant.now().toEpochMilli()));
    expMgrUser.setComment("CREATED");
    return expMgrUser;
  }

  private List<ExpMgrUser> populateDatesAndComment(List<ExpMgrUser> expMgrUsers) {
    return expMgrUsers
        .parallelStream()
        .map(this::populateDatesAndComment)
        .collect(Collectors.toList());
  }

  private ExpMgrUser handleFailure(Throwable throwable, ExpMgrUser expMgrUser) {
    if (throwable.getCause() instanceof UserAlreadyExistsException) {
      UserAlreadyExistsException userAlreadyExistsException =
          (UserAlreadyExistsException) throwable.getCause();
      return userAlreadyExistsException.getExistingUser();
    }
    LOG.error("Failed to add User {}, reason {}", expMgrUser, throwable.getMessage());
    throw new ExpMgrException(
        String.format("Failed to add User :{}, reason :{}", expMgrUser, throwable.getMessage()));
  }

  private List<ExpMgrUser> handleFailure(Throwable throwable, List<ExpMgrUser> expMgrUsers) {
    String errorMessage = "Failed to add Users {}, reason {}";
    LOG.error(errorMessage, expMgrUsers, throwable.getMessage());
    throw new ExpMgrException(String.format(errorMessage, expMgrUsers, throwable.getMessage()));
  }

  private ExpMgrUser validateIfUserNotAlreadyExists(ExpMgrUser expMgrUser) {
    validatePreRequisites(expMgrUser);
    userRepository
        .findByFirstNameAndLastNameAndEmail(
            expMgrUser.getFirstName(), expMgrUser.getLastName(), expMgrUser.geteEmail())
        .ifPresent(this::throwUserExistsException);

    return expMgrUser;
  }

  private ExpMgrUser validateIfUserExists(ExpMgrUser expMgrUser) {
    validatePreRequisites(expMgrUser);
    userRepository
        .findByFirstNameAndLastNameAndEmail(
            expMgrUser.getFirstName(), expMgrUser.getLastName(), expMgrUser.geteEmail())
        .orElseThrow(UserNotFoundException::new);

    return expMgrUser;
  }

  private List<ExpMgrUser> validateIfUserNotAlreadyExists(List<ExpMgrUser> expMgrUsers) {
    return expMgrUsers.parallelStream().filter(this::hasValidData).collect(Collectors.toList());
  }

  private boolean hasValidData(ExpMgrUser expMgrUser) {
    boolean isDataValid;
    try {
      validateIfUserNotAlreadyExists(expMgrUser);
      isDataValid = true;
    } catch (PreRequisiteFailedException requisiteFailedException) {
      isDataValid = false;
    }
    return isDataValid;
  }

  private void validatePreRequisites(ExpMgrUser expMgrUser) {
    if (expMgrUser == null) {
      handleIllegalArguments("Missing user object", null);
    }
    if (expMgrUser.getFirstName() == null
        || expMgrUser.getLastName() == null
        || expMgrUser.geteEmail() == null
        || expMgrUser.getType() == null) {
      handleIllegalArguments(
          String.format(
              "Missing One of the following arguments  FirstName :{}, or LastName :{}, or Email :{}, or type :{}",
              expMgrUser.getFirstName(),
              expMgrUser.getLastName(),
              expMgrUser.geteEmail(),
              expMgrUser.getType()),
          null);
    }
  }

  private ExpMgrUser throwUserExistsException(ExpMgrUser expMgrUserFromDB) {
    throw new UserAlreadyExistsException("User already exists in the system", expMgrUserFromDB);
  }

  private void handleIllegalArguments(String message, Object attributeName) {
    throw new PreRequisiteFailedException(String.format((message), attributeName));
  }
}
