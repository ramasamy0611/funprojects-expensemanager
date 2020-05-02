package com.ram.projects.expensemanager.rest.converter;

import com.ram.projects.expensemanager.db.entity.ExpMgrSignIn;
import com.ram.projects.expensemanager.db.entity.ExpMgrSignUp;
import com.ram.projects.expensemanager.db.entity.ExpMgrUser;
import com.ram.projects.expensemanager.exception.ExpMgrException;
import com.ram.projects.expensemanager.rest.dto.SignIn;
import com.ram.projects.expensemanager.rest.dto.SignUp;
import com.ram.projects.expensemanager.rest.dto.User;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class SingUpInputConverter implements Converter<SignUp, ExpMgrSignUp> {
  @Override
  public ExpMgrSignUp convert(SignUp input) {
    validateIfNull(input, "SignUp data is empty");
    validateIfNull(input.getSignIn(), "SignIn data is empty");
    validateIfNull(input.getUser(), "user data for registration is empty");
    return new ExpMgrSignUp(getExpMgrSignIn(input.getSignIn()), getExpMgrUser(input.getUser()));
  }

  private <T> void validateIfNull(T input, String message) {
    if (input == null) {
      throw new ExpMgrException(message);
    }
  }

  private ExpMgrSignIn getExpMgrSignIn(SignIn signIn) {
    return new ExpMgrSignIn(signIn.getUserName(), signIn.getPassword(), signIn.getEncryptionKey());
  }

  private ExpMgrUser getExpMgrUser(User user) {
    Timestamp now = Timestamp.from(Instant.now());
    ExpMgrUser expMgrUser = new ExpMgrUser();
    expMgrUser.setCreationDate(now);
    expMgrUser.setFirstName(user.getFirstName());
    expMgrUser.setLastName(user.getLastName());
    expMgrUser.seteEmail(user.getEmailId());
    expMgrUser.setModifiedDate(now);
    expMgrUser.setType(user.getType());
    return expMgrUser;
  }
}
