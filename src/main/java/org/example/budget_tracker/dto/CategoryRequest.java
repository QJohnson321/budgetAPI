package org.example.budget_tracker;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequest {
    @NotBlank @Size(max = 64)
    private String name;
}
