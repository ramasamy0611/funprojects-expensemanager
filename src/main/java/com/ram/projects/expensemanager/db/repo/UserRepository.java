package com.ram.projects.expensemanager.db.repo;

import com.ram.projects.expensemanager.db.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User save(User user);
}
