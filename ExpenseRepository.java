package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByCategoryId(Long categoryId);

    List<Expense> findByDateBetween(LocalDate start, LocalDate end);

    List<Expense> findByCategoryIdAndDateBetween(Long categoryId, LocalDate start, LocalDate end);
}
