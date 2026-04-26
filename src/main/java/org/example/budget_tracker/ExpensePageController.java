package org.example.budget_tracker;

import org.example.budget_tracker.dto.ExpenseRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Controller
@RequestMapping("/ui")
public class ExpensePageController {
    private final ExpenseService expenseService;
    private final CategoryRepository categoryRepo;

    private static final Logger logger = LoggerFactory.getLogger(ExpensePageController.class);

    public ExpensePageController(ExpenseService expenseService, CategoryRepository categoryRepo) {
        this.expenseService = expenseService;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/expenses")
    public String expensePage(Model model, @RequestParam(required = false) Long categoryId) {
        logger.info("Loading expenses page. Category filter: {}", categoryId);

        model.addAttribute("categories", categoryRepo.findAll());

        model.addAttribute("expenses", expenseService.list(null, null, categoryId));
        model.addAttribute("selectedCategoryId", categoryId);
        logger.info("Expenses page loaded successfully");
        return "expenses";
    }


    @PostMapping("/expenses")
    public String addExpense(@RequestParam BigDecimal amount,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                             @RequestParam String description,
                             @RequestParam String paymentMethod,
                             @RequestParam Long categoryId,
                             RedirectAttributes ra) {

        logger.info("Attempting to add expense. Description: {}, Amount: {}, Category ID: {}",
                description, amount, categoryId);

        ExpenseRequest req = new ExpenseRequest();
        req.amount = amount;
        req.date = date;
        req.description = description;
        req.paymentMethod = paymentMethod;
        req.categoryId = categoryId;

        expenseService.addExpense(req);
        logger.info("Expense added successfully. Description: {}", description);

        ra.addFlashAttribute("message", "Expense added successfully");
        return "redirect:/ui/expenses";
    }

    @PostMapping("/expenses/{id}/delete")
    public String deleteExpense(@PathVariable Long id, RedirectAttributes ra) {
        logger.warn("Attempting to delete expense with ID: {}", id);

        expenseService.delete(id);

        logger.info("Expense deleted successfully. ID: {}", id);

        ra.addFlashAttribute("msg", "Expense deleted successfully");
        return "redirect:/ui/expenses";
    }
}

