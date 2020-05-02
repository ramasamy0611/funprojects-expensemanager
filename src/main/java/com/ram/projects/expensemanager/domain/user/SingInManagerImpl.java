package com.ram.projects.expensemanager.domain.user;

import com.ram.projects.expensemanager.db.entity.ExpMgrSignIn;
import com.ram.projects.expensemanager.db.entity.ExpMgrSignUp;
import com.ram.projects.expensemanager.db.entity.ExpMgrUser;
import com.ram.projects.expensemanager.db.repo.SignInRepository;
import com.ram.projects.expensemanager.db.repo.UserRepository;
import com.ram.projects.expensemanager.exception.UserNameExistsException;
import com.ram.projects.expensemanager.exception.UserSignInNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class SingInManagerImpl implements ISingInManager {
  private SignInRepository signInRepository;
  private UserRepository userRepository;

  public SingInManagerImpl(SignInRepository signInRepository, UserRepository userRepository) {
    this.signInRepository = signInRepository;
    this.userRepository = userRepository;
  }

  @Override
  public CompletableFuture<ExpMgrSignIn> signIn(ExpMgrSignIn signIn) {
    return CompletableFuture.supplyAsync(() -> findUserByUserNameAndPassword(signIn));
  }

  @Override
  public CompletableFuture<String> signUp(ExpMgrSignUp expMgrSignUp) {
    return CompletableFuture.runAsync(
            () -> validateSignInExistenseByUserName(expMgrSignUp.getExpMgrSignIn().getUserName()))
        .thenAccept(noData -> registerUser(expMgrSignUp.getExpMgrUser()))
        .thenApply(noData -> encryptPasswordAndSaveSignIn(expMgrSignUp.getExpMgrSignIn()));
  }

  private void registerUser(ExpMgrUser user) {
    userRepository.saveAndFlush(user);
  }

  private String encryptPasswordAndSaveSignIn(ExpMgrSignIn expMgrSignIn) {
    String encryptedPassword = getPassword(expMgrSignIn);
    ExpMgrSignIn expMgrSignInToBeSaved =
        new ExpMgrSignIn(
            expMgrSignIn.getUserName(), encryptedPassword, expMgrSignIn.getEncryptionKey());
    String userName = signInRepository.save(expMgrSignInToBeSaved).getUserName();
    return "Congratulation!! User profile created with login name ".concat(userName);
  }

  private ExpMgrSignIn findUserByUserNameAndPassword(ExpMgrSignIn signIn) {
    String decryptedPassword = getPassword(signIn);
    return signInRepository
        .findByUserNameAndPasswordAndEncryptionKey(
            signIn.getUserName(), decryptedPassword, signIn.getEncryptionKey())
        .orElseThrow(() -> new UserSignInNotFoundException());
  }

  private String getPassword(ExpMgrSignIn signIn) {
    int hashOfKey = signIn.getEncryptionKey().hashCode();
    int hashOfPassword = signIn.getPassword().hashCode();

    return hashOfKey + signIn.getUserName() + hashOfPassword;
  }

  private void validateSignInExistenseByUserName(String userName) {
    Optional<ExpMgrSignIn> expMgrSignInOptional = signInRepository.findByUserName(userName);
    if (!expMgrSignInOptional.isEmpty()) {
      throw new UserNameExistsException();
    }
  }
}
