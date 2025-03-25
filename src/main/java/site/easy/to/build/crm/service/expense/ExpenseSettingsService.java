package site.easy.to.build.crm.service.expense;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Expense;
import site.easy.to.build.crm.entity.settings.ExpenseSettings;
import site.easy.to.build.crm.repository.ExpenseSettingsRepository;

@Service
@Slf4j
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
    
    public ExpenseSettings getLatestExpenseSettings() {
        return expenseSettingsRepository.findFirstByOrderByDateTauxDesc();
    }

    // public List<ExpenseAlert> checkExpenseAlerts(Expense expense) {
    //     List<ExpenseAlert> alerts = new ArrayList<>();
    //     try {
    //         alerts.add(checkAlertSeuil(expense));
    //     } catch (NullPointerException e) {
    //         log.info("La depense n'ateint pas le taux d'alerte");
    //     }

    //     try {
    //         alerts.add(checkBudgetDepassement(expense));
    //     } catch (NullPointerException e) {
    //         log.info("La depense n'ateint pas la limite du budget");
    //     }

    //     return alerts;
    // }
    public void checkAlertSeuil(Expense expense) {
        ExpenseSettings expenseSettings=getLatestExpenseSettings();
        Budget budget = expense.getBudget();
        double seuil = budget.getAmount().doubleValue() * expenseSettings.getTaux().doubleValue() / 100.0;
        if (expense.getAmount().doubleValue() >= seuil){
            // ExpenseAlert expenseAlert = expenseAlertService.getExpenseAlert(expense,expenseSettings.getTaux(),alertTypeService.getAlertTypeById(1), "Le montant de la depense a atteint le seuil d'alerte de "+seuil+"  de taux "+expenseSettings.getTaux()+"%");
            // return expenseAlert;
        }
    }

    public void checkBudgetDepassement(Expense expense) {
        Budget budget = expense.getBudget();
        if (expense.getAmount().doubleValue() > budget.getAmount().doubleValue()) {
            // ExpenseAlert expenseAlert = expenseAlertService.getExpenseAlert(expense,null,alertTypeService.getAlertTypeById(2), "Le montant de la depense a depasse la limite du budget de "+budget.getAmount()+"");
            // return expenseAlert;
        }
        // return null;
    }
}

