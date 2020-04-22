package com.ram.projects.expensemanager.rest.converter;

import com.ram.projects.expensemanager.db.entity.ExpMgrUser;
import com.ram.projects.expensemanager.rest.User;
import com.ram.projects.expensemanager.rest.process.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserOutputConverter implements Converter<ExpMgrUser, User> {
    @Override
    public User convert(ExpMgrUser input) {
        User user = new User(input.getId(), input.getFirstName(), input.getLastName(), input.getCreationDate().toString(), input.getModifiedDate().toString(), input.geteEmail());
        user.setComment(input.getComment());
        user.setType(user.getType());
        return user;
    }
}
