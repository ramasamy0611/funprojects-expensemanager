package com.ram.projects.expensemanager.rest.converter;

import com.ram.projects.expensemanager.common.StringUtils;
import com.ram.projects.expensemanager.db.entity.ExpMgrSignIn;
import com.ram.projects.expensemanager.rest.dto.SignIn;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SingInOutputConverter implements Converter<ExpMgrSignIn, SignIn> {
  @Override
  public SignIn convert(ExpMgrSignIn input) {
    String hiddenString = StringUtils.getHiddenString();
    return new SignIn(Instant.now().getNano(), input.getUserName(), hiddenString, hiddenString);
  }
}
