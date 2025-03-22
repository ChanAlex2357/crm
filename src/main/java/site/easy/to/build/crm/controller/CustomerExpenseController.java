package site.easy.to.build.crm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Expense;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.service.customer.CustomerServiceImpl;
import site.easy.to.build.crm.service.expense.ExpenseService;
import site.easy.to.build.crm.service.lead.LeadServiceImpl;
import site.easy.to.build.crm.service.ticket.TicketServiceImpl;
import site.easy.to.build.crm.service.user.UserServiceImpl;
import site.easy.to.build.crm.util.AuthenticationUtils;
import site.easy.to.build.crm.util.AuthorizationUtil;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/employee/expense")
public class CustomerExpenseController {

    @Autowired
    private TicketServiceImpl ticketService;
    @Autowired
    private LeadServiceImpl leadService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationUtils authenticationUtils;

    @Autowired
    private CustomerServiceImpl customerService;
    
    @Autowired
    private ExpenseService expenseService;
    
    // Show form to create a new expense
    @GetMapping("/create")
    public String showCreateExpenseForm(Model model , Authentication authentication) {
        // Verification des droits d'acces
        int userId = authenticationUtils.getLoggedInUserId(authentication);
        User user = userService.findById(userId);
        if (user.isInactiveUser()) {
            return "error/account-inactive";
        }

        List<Customer> customers;
        if (AuthorizationUtil.hasRole(authentication, "ROLE_MANAGER")) {
            customers = customerService.findAll();
        } else {
            customers = customerService.findByUserId(userId);
        }

        List<Lead> leads ;
        if (AuthorizationUtil.hasRole(authentication, "ROLE_MANAGER")) {
            leads = leadService.findAll();
        } else {
            leads = leadService.findAssignedLeads(userId);
        }


        List<Ticket> tickets;
        if (AuthorizationUtil.hasRole(authentication, "ROLE_MANAGER")) {
            tickets = ticketService.findAll();
        } else {
            tickets = ticketService.findEmployeeTickets(userId);
        }

        model.addAttribute("leads", leads);
        model.addAttribute("tickets", tickets);
        model.addAttribute("customers", customers);
        model.addAttribute("expense", new Expense());


        return "expense/create"; // corresponding Thymeleaf template
    }
    
    // Process creation of expense
    @PostMapping("/create")
    public String createExpense(@ModelAttribute("expense") @Valid Expense expense, BindingResult bindingResult) {
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
        List<Expense> expenses = expenseService.getAllExpenses();
        model.addAttribute("expenses", expenses);
        return "expense/expenses"; // Thymeleaf template for listing
    }
    
    // Show details of a specific expense
    @GetMapping("/show/{id}")
    public String showExpense(@PathVariable("id") int id, Model model) {
        Expense expense = expenseService.getExpenseById(id);
        model.addAttribute("expense", expense);
        return "expense/show";
    }
    
    // Show update form for an expense
    @GetMapping("/update/{id}")
    public String showUpdateExpenseForm(@PathVariable("id") int id, Model model) {
        Expense expense = expenseService.getExpenseById(id);
        model.addAttribute("expense", expense);
        return "expense/update";
    }
    
    // Process the update of an expense
    @PostMapping("/update")
    public String updateExpense(@ModelAttribute("expense") @Valid Expense expense, BindingResult bindingResult) {
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