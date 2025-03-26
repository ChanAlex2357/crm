package site.easy.to.build.crm.service.budget;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.exception.AdminImportException;
import site.easy.to.build.crm.repository.BudgetRepository;

@Service
@Transactional
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;
    
    // Create a new Budget
    @Transactional
    public Budget createBudget(Budget budget) {
        if (budget.getCreatedAt() == null) {
            budget.setCreatedAt(LocalDateTime.now());
        }
        return budgetRepository.save(budget);
    }
    
    // Update an existing Budget
    @Transactional
    public Budget updateBudget(Budget budget) {
        if(!budgetRepository.existsById(budget.getBudgetId())){
            throw new RuntimeException("Budget not found for id: " + budget.getBudgetId());
        }
        return budgetRepository.save(budget);
    }
    
    // Delete Budget by id
    @Transactional
    public void deleteBudget(Integer budgetId) {
        if(!budgetRepository.existsById(budgetId)){
            throw new RuntimeException("Budget not found for id: " + budgetId);
        }
        budgetRepository.deleteById(budgetId);
    }
    
    // Retrieve all Budgets
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }
    
    // Retrieve a Budget by its id
    public Budget getBudgetById(Integer budgetId) {
        return budgetRepository.findById(budgetId)
            .orElseThrow(() -> new RuntimeException("Budget not found for id: " + budgetId));
    }

    public List<Budget> findByCustomerId(int customerId) {
        return budgetRepository.findByCustomerCustomerId(customerId);
    }

    @Transactional
    public Budget createInitialBudget(Customer createdCustomer) {
        Budget budget = new Budget();
        budget.setAmount(new BigDecimal(0));
        budget.setCreatedAt(LocalDateTime.now());
        budget.setDescription("Budget initial a 0");
        budget.setCustomer(createdCustomer);
        return budgetRepository.save(budget);
    }

    public void saveAll(List<Budget> data, User manager, AdminImportException adminImportException) {
        for (int i = 0; i < data.size(); i++) {
            try {
                createBudget(data.get(i));
            } catch (Exception e) {
                adminImportException.getImportException(i+1).addError(e);
            }
        }
    }
}
