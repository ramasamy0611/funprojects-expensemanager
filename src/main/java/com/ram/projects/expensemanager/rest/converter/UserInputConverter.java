package com.ram.projects.expensemanager.rest.converter;


import com.ram.projects.expensemanager.db.entity.ExpMgrUser;
import com.ram.projects.expensemanager.rest.dto.User;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class UserInputConverter implements Converter<User, ExpMgrUser> {
    @Override
    public ExpMgrUser convert(User user) {
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
