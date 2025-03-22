package site.easy.to.build.crm.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Devise;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.service.budget.DeviseService;
import site.easy.to.build.crm.service.customer.CustomerServiceImpl;
import site.easy.to.build.crm.service.user.UserServiceImpl;
import site.easy.to.build.crm.util.AuthenticationUtils;
import site.easy.to.build.crm.util.AuthorizationUtil;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import site.easy.to.build.crm.service.budget.BudgetService;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    private final DeviseService deviseService;

    private final UserServiceImpl userService;

    private final AuthenticationUtils authenticationUtils;

    private final CustomerServiceImpl customerService;

    BudgetController(CustomerServiceImpl customerServiceImpl, AuthenticationUtils authenticationUtils, UserServiceImpl userServiceImpl, DeviseService deviseService) {
        this.customerService = customerServiceImpl;
        this.authenticationUtils = authenticationUtils;
        this.userService = userServiceImpl;
        this.deviseService = deviseService;
    }

    @GetMapping("/employee/budget/create")
    public String createBudget(Model model, Authentication authentication) {
        // Verification des droits d'acces
        int userId = authenticationUtils.getLoggedInUserId(authentication);
        User user = userService.findById(userId);
        if (user.isInactiveUser()) {
            return "error/account-inactive";
        }
        // Preparation des donnees de formulaire
        List<Customer> customers;
        if (AuthorizationUtil.hasRole(authentication, "ROLE_MANAGER")) {
            customers = customerService.findAll();
        } else {
            customers = customerService.findByUserId(userId);
        }
        List<Devise> currencies = deviseService.findAll();

        model.addAttribute("customers", customers);
        model.addAttribute("budget", new Budget());
        model.addAttribute("currencies", currencies);
        return "budget/create";
    }

    @PostMapping("/employee/budget/create")
    public String createBudget(@ModelAttribute("budget") @Valid Budget budget, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            // Return to the create view if validation errors occur
            return "budget/create";
        }
        budgetService.createBudget(budget);
        return "redirect:/employee/budget/show-all";
    }

    @GetMapping("/employee/budget/show-all")
    public String showAllBudgets(Model model, Authentication authentication) {
        // Verification des droits d'acces
        int userId = authenticationUtils.getLoggedInUserId(authentication);
        User user = userService.findById(userId);
        if (user.isInactiveUser()) {
            return "error/account-inactive";
        }
        model.addAttribute("budgets", budgetService.getAllBudgets());
        return "budget/budgets";
    }

    @GetMapping("/employee/budget/show/{id}")
    public String showBudget(@PathVariable("id") int id, Model model, Authentication authentication) {
        Budget budget = budgetService.getBudgetById(id);
        if (budget == null) {
            return "error/not-found";
        }
        // Optional: add authorization check if needed.
        model.addAttribute("budget", budget);
        return "budget/show";
    }

    @GetMapping("/employee/budget/update/{id}")
    public String showBudgetUpdateForm(@PathVariable("id") int id, Model model, Authentication authentication) {
        Budget budget = budgetService.getBudgetById(id);
        if(budget == null) {
            return "error/not-found";
        }
        int userId = authenticationUtils.getLoggedInUserId(authentication);
        User user = userService.findById(userId);
        if(user.isInactiveUser()){
            return "error/account-inactive";
        }
        // Load customers and currencies for dropdowns
        List<Customer> customers = customerService.findByUserId(userId);
        if(AuthorizationUtil.hasRole(authentication, "ROLE_MANAGER")){
            customers = customerService.findAll();
        }
        List<Devise> currencies = deviseService.findAll();
        
        model.addAttribute("customers", customers);
        model.addAttribute("currencies", currencies);
        model.addAttribute("budget", budget);
        return "budget/update";
    }

    @PostMapping("/employee/budget/update")
    public String updateBudget(
        @ModelAttribute("budget") @Valid Budget budget,
        BindingResult bindingResult,
        Model model,
        Authentication authentication,
        @RequestParam("customerId") int customerId,
        @RequestParam("currency") int currencyId
    ) {
        int userId = authenticationUtils.getLoggedInUserId(authentication);
        User user = userService.findById(userId);
        if(user.isInactiveUser()){
            return "error/account-inactive";
        }
        Customer customer = customerService.findByCustomerId(customerId);
        if (customer == null || (customer.getUser().getId() != userId && !AuthorizationUtil.hasRole(authentication, "ROLE_MANAGER"))) {
            return "error/500";
        }
        Devise currency = deviseService.getDeviseById(currencyId);
        if (currency == null) {
            return "error/500";
        }

        if(bindingResult.hasErrors()){
            List<Customer> customers = customerService.findByUserId(userId);
            if(AuthorizationUtil.hasRole(authentication, "ROLE_MANAGER")){
                customers = customerService.findAll();
            }
            List<Devise> currencies = deviseService.findAll();
            model.addAttribute("customers", customers);
            model.addAttribute("currencies", currencies);
            return "budget/update";
        }

        // Preserve creation date (or any other property) if needed
        log.info("Updating budget: {}", budget);
        Budget existingBudget = budgetService.getBudgetById(budget.getBudgetId());
        
        
        budget.setCreatedAt(existingBudget.getCreatedAt());
        budget.setCurrency(currency);
        budget.setCustomer(customer);
        
        budgetService.updateBudget(budget);

        return "redirect:/employee/budget/show-all";
    }

    @PostMapping("/employee/budget/delete/{id}")
    public String deleteBudget(@PathVariable("id") int id, Authentication authentication) {
        Budget budget = budgetService.getBudgetById(id);
        if(budget == null) {
            return "error/not-found";
        }
        int userId = authenticationUtils.getLoggedInUserId(authentication);
        User user = userService.findById(userId);
        if(user.isInactiveUser()){
            return "error/account-inactive";
        }
        // Optional: check if user is authorized to delete the budget
        budgetService.deleteBudget(id);
        return "redirect:/employee/budget/show-all";
    }
}