package com.ram.projects.expensemanager.db.repo;

import com.ram.projects.expensemanager.db.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {}
