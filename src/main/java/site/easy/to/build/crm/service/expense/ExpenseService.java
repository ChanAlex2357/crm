package site.easy.to.build.crm.service.expense;


import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Expense;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.entity.dto.ExpenseTotalDTO;
import site.easy.to.build.crm.entity.dto.LeadDTO;
import site.easy.to.build.crm.exception.AdminImportException;
import site.easy.to.build.crm.exception.ImportException;
import site.easy.to.build.crm.repository.ExpenseRepository;
import site.easy.to.build.crm.repository.LeadRepository;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.service.ticket.TicketService;

@Service
public class ExpenseService {

    @Autowired
    private Validator validator;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    @Lazy
    private LeadService leadService;
    
    @Autowired
    @Lazy
    private TicketService ticketService;

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
    public Expense createCustomerExpense(double amount,String dateExpense,Customer customer,Ticket ticket) {
        Expense expense = new Expense();
        expense.setTicket(ticket);
        return createCustomerExpense(amount, dateExpense, customer, expense);
    }
    @Transactional
    public Expense createCustomerExpense(double amount,String dateExpense,Customer customer,Expense expense) {
        expense.setAmount(BigDecimal.valueOf(amount));
        expense.setDateExpense(dateExpense);
        expense.setCustomer(customer);
        createExpense(expense);
        return expense;
    }

    @Transactional
    public Expense createCustomerExpense(double amount,String dateExpense,Customer customer,Expense expense,BindingResult bindingResult) {
    
        Set<ConstraintViolation<Expense>> violations = validator.validate(expense);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Expense> violation : violations) {
                bindingResult.rejectValue(violation.getPropertyPath().toString(), "", violation.getMessage());
            }
        }

        if (bindingResult.hasErrors()) {
            return null;
        }
        return createCustomerExpense(amount, dateExpense,customer, expense);
    }
    
    @Transactional
    public Expense createCustomerExpense(double amount,String dateExpense,Customer customer,Lead lead) {
        Expense expense = new Expense();
        expense.setLead(lead);
        return createCustomerExpense(amount, dateExpense, customer, expense);
    }

    @Transactional
    public void saveAll(List<Expense> data,User manager,AdminImportException importException) {

        int line = 1;
        
        for (Expense expense : data) {
            try {
            if (expense.getLead() == null && expense.getTicket() == null) { 
                    throw new IllegalArgumentException("Expense must have a lead or a ticket.");
                }
                
                if (expense.getLead() != null) {
                    expense.getLead().setManager(manager);
                    expense.getLead().setEmployee(manager);
                    Lead ld =leadService.save(expense.getLead());
                    expense.setLead(ld);
                }
                else if (expense.getTicket() != null) {
                    expense.getTicket().setManager(manager);
                    expense.getTicket().setEmployee(manager);
                    Ticket tk = ticketService.save(expense.getTicket());
                    expense.setTicket(tk);
                }
                createExpense(expense);
            } catch (Exception e) {
                importException.getImportException(line).addError(e.getMessage());
            }
        }
    }

    @Transactional
    public void deleteExpenseOf(Lead lead) {
        List<Expense> expenses = expenseRepository.findByLeadLeadId(lead.getLeadId());
        if (expenses.isEmpty()) {
            return;
        }
        deleteExpense(expenses.get(0).getExpenseId());
    }

    @Transactional
    public void deleteExpenseOf(Ticket ticket) {
        List<Expense> expenses = expenseRepository.findByTicketTicketId(ticket.getTicketId());
        if (expenses.isEmpty()) {
            return;
        }
        deleteExpense(expenses.get(0).getExpenseId());
    }

    public List<Expense> getExpenseByLead(int leadId) {
        return expenseRepository.findByLeadLeadId(leadId);
    }


    public List<ExpenseTotalDTO> getAllExpensesWithDetails() {
        return expenseRepository.findAllExpensesWithDetails();
    }
}

