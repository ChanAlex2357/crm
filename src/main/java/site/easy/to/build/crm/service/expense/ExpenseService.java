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
import site.easy.to.build.crm.entity.dto.ExpenseTotalDTO;
import site.easy.to.build.crm.repository.ExpenseRepository;
import site.easy.to.build.crm.service.budget.BudgetService;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseSettingsService expenseSettingsService;

    @Autowired
    private ExpenseAlertService expenseAlertService;
    @Autowired
    private Validator validator;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private BudgetService budgetService;
    
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
    public Expense createExpense(double amount,String dateExpense,Budget budget,Customer customer,Ticket ticket) {
        Expense expense = new Expense();
        expense.setTicket(ticket);
        return createExpense(amount, dateExpense, budget, customer, expense);
    }
    @Transactional
    public Expense createExpense(double amount,String dateExpense,Budget budget,Customer customer,Expense expense) {
        expense.setAmount(BigDecimal.valueOf(amount));
        expense.setDateExpense(dateExpense);
        expense.setCustomer(customer);
        expense.setBudget(budget);

        createExpense(expense);
        List<ExpenseAlert> alerts = expenseSettingsService.checkExpenseAlerts(expense);
        System.out.println(alerts);
        for (ExpenseAlert expenseAlert : alerts) {
            expenseAlertService.createExpenseAlert(expenseAlert);
        }

        // Calculer le reste du budget
        double reste = budget.getAmount().doubleValue() - expense.getDoubleAmount();
        if (reste < 0) {reste = 0;}
        budget.setReste(BigDecimal.valueOf(reste));
        // Mettre a jour le budget
        budgetService.updateBudget(budget);

        return expense;
    }

    @Transactional
    public Expense createExpense(double amount,String dateExpense,Budget budget,Customer customer,Expense expense,BindingResult bindingResult) {
    
        Set<ConstraintViolation<Expense>> violations = validator.validate(expense);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Expense> violation : violations) {
                bindingResult.rejectValue(violation.getPropertyPath().toString(), "", violation.getMessage());
            }
        }

        if (bindingResult.hasErrors()) {
            return null;
        }
        return createExpense(amount, dateExpense, budget, customer, expense);
    }
    
    @Transactional
    public Expense createExpense(double amount,String dateExpense,Budget budget,Customer customer,Lead lead) {
        Expense expense = new Expense();
        expense.setLead(lead);
        return createExpense(amount, dateExpense, budget, customer, expense);
    }

    public List<ExpenseTotalDTO> getAllExpensesWithDetails() {
        return expenseRepository.findAllExpensesWithDetails();
    }
    
    public List<ExpenseTotalDTO> getExpensesByCustomerId(Integer customerId) {
        return expenseRepository.findExpensesByCustomerId(customerId);
    }

    public List<Expense> getExpenseByBudget(Budget budget){
        return getExpenseByBudget(budget.getBudgetId());
    }
    public List<Expense> getExpenseByBudget(int budget_id){
        return expenseRepository.findByBudgetBudgetId(budget_id);
    }

    public List<Expense> getExpenseByLead(int leadId) {
        return expenseRepository.findByLeadLeadId(leadId);
    }

    public List<Expense> getExpenseByTicket(int ticketId) {
        return expenseRepository.findByTicketTicketId(ticketId);
    }

    public void deleteExpenseOf(Lead lead){
        try {
            Expense eexpense = getExpenseByLead(lead.getLeadId()).get(0);
            deleteExpense(eexpense.getExpenseId());
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Le lead n'a pas d'expense");
        }
    }
    public void deleteExpenseOf(Ticket ticket){
        try {
            Expense eexpense = getExpenseByTicket(ticket.getTicketId()).get(0);
            deleteExpense(eexpense.getExpenseId());
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Le lead n'a pas d'expense");
        }
    }
}

