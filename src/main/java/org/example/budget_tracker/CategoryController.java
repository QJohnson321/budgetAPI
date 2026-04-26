package org.example.budget_tracker;
import org.example.budget_tracker.dto.CategoryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.example.budget_tracker.CategoryRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryRepository repo;
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);


    public CategoryController(CategoryRepository repo) {
        this.repo = repo;

    }

    @GetMapping
    public List<Category> getAll() {logger.info("Fetching all categories");
        return repo.findAll();}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category create( Category boy) {

        String name = boy.getName() == null ? "" : boy.getName().trim();

        logger.info("Attempting to create category: {}", name);

        if (name.isBlank()) {
            logger.error("Category name cannot be blank");
            throw new RuntimeException("Name cannot be blank");
        }

        if (repo.existsByNameIgnoreCase(name)) {
            logger.error("Category with name {} already exists", name);
            throw new RuntimeException("Category already exists");
        }

        logger.info("Category created successfully");
        return repo.save(new Category(name));
    }
}
