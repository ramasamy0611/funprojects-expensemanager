package com.ram.projects.expensemanager.db.repo;

import com.ram.projects.expensemanager.db.entity.ExpMgrUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<ExpMgrUser, Long> {
    ExpMgrUser save(ExpMgrUser expMgrUser);
}
