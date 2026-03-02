package org.example.budget_tracker;
import com.example.demo.dto.ExpenseRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")

public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {

        this.service = service;
    }

    @GetMapping
    public List<Expense> list(@RequestParam(required = false)LocalDate from,
                              @RequestParam(required = false)LocalDate to,
                              @RequestParam(required = false) Long categoryId) {

            return service.list(from, to, categoryId);
    }

    @PostMapping
   public ResponseEntity<Expense> create(@RequestBody ExpenseRequest req) {
        Expense saved = service.addExpense(req);
        return ResponseEntity.created(URI.create("/api/expenses/" + saved.getId()))
                .body(saved);

    }


    @PutMapping("/{id}")
    public Expense update(@PathVariable Long id, @RequestBody ExpenseRequest req){
        return service.updateExpense(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
