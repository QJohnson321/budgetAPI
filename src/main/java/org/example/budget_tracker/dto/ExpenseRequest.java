package org.example.budget_tracker.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseRequest {
    @NotNull @Positive public BigDecimal amount;
    @NotNull public LocalDate date;
    @Size(max = 255) public String description;
   @Size(max = 50) public String paymentMethod;

    // Provide ONE of these when creating/updating:
    public Long categoryId;      // existing category
    @Size(max = 64) public String categoryName;  // or find/create by name
}
