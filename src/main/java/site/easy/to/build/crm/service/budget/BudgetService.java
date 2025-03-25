package site.easy.to.build.crm.service.budget;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.repository.BudgetRepository;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;
    
    // Create a new Budget
    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }
    
    // Update an existing Budget
    public Budget updateBudget(Budget budget) {
        if(!budgetRepository.existsById(budget.getBudgetId())){
            throw new RuntimeException("Budget not found for id: " + budget.getBudgetId());
        }
        return budgetRepository.save(budget);
    }
    
    // Delete Budget by id
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
}
