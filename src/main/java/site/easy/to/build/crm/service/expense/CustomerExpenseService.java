package site.easy.to.build.crm.service.expense;


import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.CustomerExpense;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.repository.CustomerExpenseRepository;

@Service
public class CustomerExpenseService {

    @Autowired
    private Validator validator;

    @Autowired
    private CustomerExpenseRepository expenseRepository;
    
    // Create a new expense
    public CustomerExpense createExpense(CustomerExpense expense) {
        return expenseRepository.save(expense);
    }
    
    // Update an existing expense
    public CustomerExpense updateExpense(CustomerExpense expense) {
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
    public CustomerExpense getExpenseById(int id) {
        return expenseRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
    }
    
    // Retrieve all expenses
    public List<CustomerExpense> getAllExpenses() {
        return expenseRepository.findAll();
    }


    public CustomerExpense createCustomerExpense(double amount,String dateExpense,Budget budget,Customer customer,Ticket ticket) {
        CustomerExpense expense = new CustomerExpense();
        expense.setTicket(ticket);
        return createCustomerExpense(amount, dateExpense, budget, customer, expense);
    }
    public CustomerExpense createCustomerExpense(double amount,String dateExpense,Budget budget,Customer customer,CustomerExpense expense) {
        expense.setAmount(BigDecimal.valueOf(amount));
        expense.setDateExpense(dateExpense);
        expense.setCustomer(customer);
        expense.setBudget(budget);
        createExpense(expense);
        
        return expense;
    }

    public CustomerExpense createCustomerExpense(double amount,String dateExpense,Budget budget,Customer customer,CustomerExpense expense,BindingResult bindingResult) {
    
        Set<ConstraintViolation<CustomerExpense>> violations = validator.validate(expense);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<CustomerExpense> violation : violations) {
                bindingResult.rejectValue(violation.getPropertyPath().toString(), "", violation.getMessage());
            }
        }

        if (bindingResult.hasErrors()) {
            return null;
        }
        return createCustomerExpense(amount, dateExpense, budget, customer, expense);
    }
    
    public CustomerExpense createCustomerExpense(double amount,String dateExpense,Budget budget,Customer customer,Lead lead) {
        CustomerExpense expense = new CustomerExpense();
        expense.setLead(lead);
        return createCustomerExpense(amount, dateExpense, budget, customer, expense);
    }
}

