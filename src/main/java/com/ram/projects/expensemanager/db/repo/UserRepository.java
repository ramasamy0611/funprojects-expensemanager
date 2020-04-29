package com.ram.projects.expensemanager.db.repo;

import com.ram.projects.expensemanager.db.entity.ExpMgrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ExpMgrUser, Long> {
    ExpMgrUser save(ExpMgrUser expMgrUser);

    Optional<ExpMgrUser> findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String eMail);
}
