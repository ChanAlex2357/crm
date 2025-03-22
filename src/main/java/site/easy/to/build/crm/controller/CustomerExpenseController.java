package site.easy.to.build.crm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.entity.CustomerExpense;
import site.easy.to.build.crm.service.expense.CustomerExpenseService;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/employee/expense")
public class CustomerExpenseController {

    @Autowired
    private CustomerExpenseService expenseService;
    
    // Show form to create a new expense
    @GetMapping("/create")
    public String showCreateExpenseForm(Model model) {
        model.addAttribute("expense", new CustomerExpense());
        return "expense/create"; // corresponding Thymeleaf template
    }
    
    // Process creation of expense
    @PostMapping("/create")
    public String createExpense(@ModelAttribute("expense") @Valid CustomerExpense expense, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "expense/create";
        }
        expense.setCreatedAt(LocalDateTime.now());
        expense.setUpdatedAt(LocalDateTime.now());
        // Assume dateExpense is already populated (or set it here if needed)
        expenseService.createExpense(expense);
        return "redirect:/employee/expense/show-all";
    }
    
    // List all expenses
    @GetMapping("/show-all")
    public String showAllExpenses(Model model) {
        List<CustomerExpense> expenses = expenseService.getAllExpenses();
        model.addAttribute("expenses", expenses);
        return "expense/expenses"; // Thymeleaf template for listing
    }
    
    // Show details of a specific expense
    @GetMapping("/show/{id}")
    public String showExpense(@PathVariable("id") int id, Model model) {
        CustomerExpense expense = expenseService.getExpenseById(id);
        model.addAttribute("expense", expense);
        return "expense/show";
    }
    
    // Show update form for an expense
    @GetMapping("/update/{id}")
    public String showUpdateExpenseForm(@PathVariable("id") int id, Model model) {
        CustomerExpense expense = expenseService.getExpenseById(id);
        model.addAttribute("expense", expense);
        return "expense/update";
    }
    
    // Process the update of an expense
    @PostMapping("/update")
    public String updateExpense(@ModelAttribute("expense") @Valid CustomerExpense expense, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "expense/update";
        }
        expense.setUpdatedAt(LocalDateTime.now());
        expenseService.updateExpense(expense);
        return "redirect:/employee/expense/show-all";
    }
    
    // Delete an expense
    @PostMapping("/delete/{id}")
    public String deleteExpense(@PathVariable("id") int id) {
        expenseService.deleteExpense(id);
        return "redirect:/employee/expense/show-all";
    }
}