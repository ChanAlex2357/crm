package site.easy.to.build.crm.service.expense;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.CustomerExpense;
import site.easy.to.build.crm.repository.CustomerExpenseRepository;

@Service
public class CustomerExpenseService {

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
}
