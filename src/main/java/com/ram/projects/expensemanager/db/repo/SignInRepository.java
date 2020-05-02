package com.ram.projects.expensemanager.db.repo;

import com.ram.projects.expensemanager.db.entity.ExpMgrSignIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignInRepository extends JpaRepository<ExpMgrSignIn, Long> {
  Optional<ExpMgrSignIn> findByUserNameAndPasswordAndEncryptionKey(
      @Param("userName") String userName,
      @Param("password") String password,
      @Param("encryptionKey") String encryptionKey);

  Optional<ExpMgrSignIn> findByUserName(@Param("userName") String userName);
}
