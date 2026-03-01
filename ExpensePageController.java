package org.example.budget_tracker;

import com.example.demo.dto.ExpenseRequest;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;




@Controller
@RequestMapping("/ui")
public class ExpensePageController {
    private final ExpenseService expenseService;
    private final CategoryRepository categoryRepo;

    public ExpensePageController(ExpenseService expenseService, CategoryRepository categoryRepo) {
        this.expenseService = expenseService;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/expenses")
    public String expensePage(Model model, @RequestParam(required = false) Long categoryId) {
        model.addAttribute("categories", categoryRepo.findAll());

        model.addAttribute("expenses", expenseService.list(null, null, categoryId));
        model.addAttribute("selectedCategoryId", categoryId);
        return "expenses";
    }


    @PostMapping("/expenses")
    public String addExpense(@RequestParam BigDecimal amount,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                             @RequestParam String description,
                             @RequestParam String paymentMethod,
                             @RequestParam Long categoryId,
                             RedirectAttributes ra) {

        ExpenseRequest req = new ExpenseRequest();
        req.amount = amount;
        req.date = date;
        req.description = description;
        req.paymentMethod = paymentMethod;
        req.categoryId = categoryId;

        expenseService.addExpense(req);
        ra.addFlashAttribute("message", "Expense added successfully");
        return "redirect:/ui/expenses";
    }

    @PostMapping("/expenses/{id}/delete")
    public String deleteExpense(@PathVariable Long id, RedirectAttributes ra) {
        expenseService.delete(id);
        ra.addFlashAttribute("message", "Expense deleted successfully");
        return "redirect:/expenses";
    }
}

