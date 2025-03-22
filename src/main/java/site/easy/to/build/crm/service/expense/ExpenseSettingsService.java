package site.easy.to.build.crm.service.expense;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.settings.ExpenseSettings;
import site.easy.to.build.crm.repository.ExpenseSettingsRepository;

@Service
public class ExpenseSettingsService {

    @Autowired
    private ExpenseSettingsRepository expenseSettingsRepository;
    
    public List<ExpenseSettings> getAllExpenseSettings() {
        return expenseSettingsRepository.findAll();
    }
    
    public ExpenseSettings getExpenseSettingsById(int id) {
        return expenseSettingsRepository.findById(id).orElse(null);
    }
    
    public ExpenseSettings createExpenseSettings(ExpenseSettings settings) {
        return expenseSettingsRepository.save(settings);
    }
    
    public ExpenseSettings updateExpenseSettings(ExpenseSettings settings) {
        return expenseSettingsRepository.save(settings);
    }
    
    public void deleteExpenseSettings(int id) {
        expenseSettingsRepository.deleteById(id);
    }
}

