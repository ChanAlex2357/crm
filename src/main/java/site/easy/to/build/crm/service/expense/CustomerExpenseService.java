package site.easy.to.build.crm.service.expense;


import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Expense;
import site.easy.to.build.crm.entity.ExpenseAlert;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.repository.CustomerExpenseRepository;

@Service
public class CustomerExpenseService {

    @Autowired
    private ExpenseSettingsService expenseSettingsService;

    @Autowired
    private ExpenseAlertService expenseAlertService;
    @Autowired
    private Validator validator;

    @Autowired
    private CustomerExpenseRepository expenseRepository;
    
    @Transactional
    // Create a new expense
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
    
    // Update an existing expense
    public Expense updateExpense(Expense expense) {
        if(!expenseRepository.existsById(expense.getExpenseId())){
            throw new RuntimeException("Expense not found with id: " + expense.getExpenseId());
        }
        return expenseRepository.save(expense);
    }
    
    // Delete an expense by id
    public void deleteExpense(int id) {
        if(!expenseRepository.existsById(id)){
            throw new RuntimeException("Expense not found with id: " + id);
        }
        expenseRepository.deleteById(id);
    }
    
    // Retrieve an expense by id
    public Expense getExpenseById(int id) {
        return expenseRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
    }
    
    // Retrieve all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Transactional
    public Expense createCustomerExpense(double amount,String dateExpense,Budget budget,Customer customer,Ticket ticket) {
        Expense expense = new Expense();
        expense.setTicket(ticket);
        return createCustomerExpense(amount, dateExpense, budget, customer, expense);
    }
    @Transactional
    public Expense createCustomerExpense(double amount,String dateExpense,Budget budget,Customer customer,Expense expense) {
        expense.setAmount(BigDecimal.valueOf(amount));
        expense.setDateExpense(dateExpense);
        expense.setCustomer(customer);
        expense.setBudget(budget);
        createExpense(expense);
        List<ExpenseAlert> alerts = expenseSettingsService.checkExpenseAlerts(expense);
        for (ExpenseAlert expenseAlert : alerts) {
            expenseAlertService.createExpenseAlert(expenseAlert);
        }
        
        return expense;
    }

    @Transactional
    public Expense createCustomerExpense(double amount,String dateExpense,Budget budget,Customer customer,Expense expense,BindingResult bindingResult) {
    
        Set<ConstraintViolation<Expense>> violations = validator.validate(expense);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Expense> violation : violations) {
                bindingResult.rejectValue(violation.getPropertyPath().toString(), "", violation.getMessage());
            }
        }

        if (bindingResult.hasErrors()) {
            return null;
        }
        return createCustomerExpense(amount, dateExpense, budget, customer, expense);
    }
    
    @Transactional
    public Expense createCustomerExpense(double amount,String dateExpense,Budget budget,Customer customer,Lead lead) {
        Expense expense = new Expense();
        expense.setLead(lead);
        return createCustomerExpense(amount, dateExpense, budget, customer, expense);
    }
}

