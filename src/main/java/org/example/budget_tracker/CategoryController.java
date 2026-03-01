package org.example.budget_tracker;
import org.example.budget_tracker.dto.CategoryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.example.budget_tracker.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryRepository repo;


    public CategoryController(CategoryRepository repo) {
        this.repo = repo;

    }

    @GetMapping
    public List<Category> getAll() {return repo.findAll();}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody Category boy) {
        String name = boy.getName() == null ? "" : boy.getName().trim();
        if (name.isBlank()) throw new RuntimeException("Name cannot be blank");
        if (repo.existsByNameIgnoreCase(name)) throw new RuntimeException("Category already exists");
        return repo.save(new Category(name));
    }
}
