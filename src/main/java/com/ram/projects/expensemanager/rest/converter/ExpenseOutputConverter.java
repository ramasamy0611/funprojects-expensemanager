package com.ram.projects.expensemanager.rest.converter;

import org.springframework.stereotype.Component;

@Component
public class ExpenseOutputConverter implements Converter<Long, Long> {
  @Override
  public Long convert(Long expenseId) {
    return expenseId;
  }
}
