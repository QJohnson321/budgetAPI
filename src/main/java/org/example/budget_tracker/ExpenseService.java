package org.example.budget_tracker;

import com.example.demo.dto.ExpenseRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepo;
    private final CategoryRepository categoryRepo;

    public ExpenseService(ExpenseRepository expenseRepo, CategoryRepository categoryRepo) {
        this.expenseRepo = expenseRepo;
        this.categoryRepo = categoryRepo;
    }

    @Transactional
    public Expense addExpense(ExpenseRequest req) {
        Category cat = resolveCategory(req);
        Expense e = new Expense();
        e.setAmount(req.amount);
        e.setDate(req.date);
        e.setDescription(req.description);
        e.setPaymentMethod(req.paymentMethod);
        e.setCategory(cat);
        return expenseRepo.save(e);
    }

    @Transactional
    public Expense updateExpense(Long id, ExpenseRequest req) {
        Expense e = expenseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (req.amount != null)        e.setAmount(req.amount);
        if (req.date != null)          e.setDate(req.date);
        if (req.description != null)   e.setDescription(req.description);
        if (req.paymentMethod != null) e.setPaymentMethod(req.paymentMethod);

        if (req.categoryId != null || (req.categoryName != null && !req.categoryName.isBlank())) {
            e.setCategory(resolveCategory(req));
        }
        return expenseRepo.save(e);
    }

    @Transactional
    public void delete(Long id) {
        if (!expenseRepo.existsById(id)) {
            throw new RuntimeException("Expense not found");
        }
        expenseRepo.deleteById(id);
    }

    public List<Expense> list(LocalDate from, LocalDate to, Long categoryId) {
        if(to == null) to = LocalDate.now();
        if(from == null) from = to.minusDays(30);

        return (categoryId == null)
                ? expenseRepo.findByDateBetween(from, to)
                : expenseRepo.findByCategoryIdAndDateBetween(categoryId, from, to);
    }

    private Category resolveCategory(ExpenseRequest req) {
        if (req.categoryId != null) {
            return categoryRepo.findById(req.categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
        }
        if (req.categoryName != null && !req.categoryName.isBlank()) {
            String name = req.categoryName.trim();
            return categoryRepo.findByNameIgnoreCase(name)
                    .orElseGet(() -> categoryRepo.save(new Category(name)));
        }
        throw new RuntimeException("categoryId or categoryName is required");
    }
}
